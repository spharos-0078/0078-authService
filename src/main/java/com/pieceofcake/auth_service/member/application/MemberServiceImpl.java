package com.pieceofcake.auth_service.member.application;

import com.pieceofcake.auth_service.common.util.JwtUtil;
import com.pieceofcake.auth_service.common.util.RedisUtil;
import com.pieceofcake.auth_service.member.dto.in.LoginRequestDto;
import com.pieceofcake.auth_service.member.dto.in.SignUpRequestDto;
import com.pieceofcake.auth_service.member.dto.out.LoginResponseDto;
import com.pieceofcake.auth_service.member.entity.Member;
import com.pieceofcake.auth_service.member.entity.enums.MemberStatus;
import com.pieceofcake.auth_service.member.infrastructure.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;

    @Transactional
    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) {

        if (memberRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        if (memberRepository.existsByPhoneNumber(signUpRequestDto.getPhoneNumber())) {
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
        }
        if (memberRepository.existsByNickname(signUpRequestDto.getNickname())) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
        if (!"true".equals(redisUtil.get("sms:SIGN_UP:Verified:" + signUpRequestDto.getPhoneNumber()))) {
            throw new IllegalArgumentException("인증 코드가 존재하지 않습니다.");
        }

        memberRepository.save(signUpRequestDto.toEntity(passwordEncoder));
    }

    @Transactional
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        final Member member = memberRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        // 비밀번호 검증. 차후 배포 단계에서 에러 메시지 자세한 내역은 숨길 것.
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        } else if (member.getStatus() == MemberStatus.DISABLED) {
            throw new IllegalArgumentException("비활성화된 회원입니다.");
        } else if (member.getStatus() == MemberStatus.BLACKED) {
            throw new IllegalArgumentException("블랙리스트에 등록된 회원입니다.");
        } else if (member.getStatus() == MemberStatus.DELETED) {
            throw new IllegalArgumentException("삭제된 회원입니다.");
        }

        return jwtUtil.createLoginToken(member.getMemberUuid());

    }


}
