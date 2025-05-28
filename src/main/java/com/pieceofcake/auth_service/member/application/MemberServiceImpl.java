package com.pieceofcake.auth_service.member.application;

import com.pieceofcake.auth_service.common.entity.BaseResponseStatus;
import com.pieceofcake.auth_service.common.exception.BaseException;
import com.pieceofcake.auth_service.common.jwt.JwtProvider;
import com.pieceofcake.auth_service.common.util.JwtUtil;
import com.pieceofcake.auth_service.common.util.RedisUtil;
import com.pieceofcake.auth_service.member.dto.in.CheckEmailRequestDto;
import com.pieceofcake.auth_service.member.dto.in.LoginRequestDto;
import com.pieceofcake.auth_service.member.dto.in.SignUpRequestDto;
import com.pieceofcake.auth_service.member.dto.out.CheckEmailResponseDto;
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
    private final JwtProvider jwtProvider;
    private final JwtUtil jwtUtil;

    @Transactional
    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) {

        if (memberRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
        }
        if (memberRepository.existsByPhoneNumber(signUpRequestDto.getPhoneNumber())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_PHONE_NUMBER);
        }
        if (memberRepository.existsByNickname(signUpRequestDto.getNickname())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_NICKNAME);
        }
        if (!"true".equals(redisUtil.get("sms:SIGN_UP:Verified:" + signUpRequestDto.getPhoneNumber()))) {
            throw new BaseException(BaseResponseStatus.SMS_VERIFICATION_NOT_COMPLETED);
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
            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
        } else if (member.getStatus() == MemberStatus.DISABLED) {
            throw new BaseException(BaseResponseStatus.DISABLED_MEMBER);
        } else if (member.getStatus() == MemberStatus.BLACKED) {
            throw new BaseException(BaseResponseStatus.BLACKED_MEMBER);
        } else if (member.getStatus() == MemberStatus.DELETED) {
            throw new BaseException(BaseResponseStatus.DELETED_MEMBER);
        }

        return jwtUtil.createLoginToken(member.getMemberUuid());

    }

    @Transactional
    @Override
    public void logout(String memberUuid) {
        redisUtil.delete("Access:" + memberUuid);
        log.info("로그아웃 성공: {}", memberUuid);
    }

    @Override
    public CheckEmailResponseDto checkEmail(CheckEmailRequestDto checkEmailRequestDto) {
        boolean available = !memberRepository.existsByEmail(checkEmailRequestDto.getEmail());
        log.info("이메일: {}, 사용 가능 여부: {}", checkEmailRequestDto.getEmail(), available);

        return CheckEmailResponseDto.of(available);
    }
}
