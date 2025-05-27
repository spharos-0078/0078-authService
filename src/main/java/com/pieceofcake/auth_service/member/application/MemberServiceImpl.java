package com.pieceofcake.auth_service.member.application;

import com.pieceofcake.auth_service.common.util.RedisUtil;
import com.pieceofcake.auth_service.member.dto.in.SignUpRequestDto;
import com.pieceofcake.auth_service.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtil redisUtil;

    public void signUp(SignUpRequestDto signUpRequestDto) {

        if (memberRepository.existsByEmail(signUpRequestDto.getEmail())) {
            System.out.println("email.");
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        if (memberRepository.existsByPhoneNumber(signUpRequestDto.getPhoneNumber())) {
            System.out.println("phone");
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
        }
        if (memberRepository.existsByNickname(signUpRequestDto.getNickname())) {
            System.out.println("nick.");
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
        System.out.println("why not?");
        System.out.println(redisUtil.get("sms:SIGN_UP:Verified:" + signUpRequestDto.getPhoneNumber()));
        if (!"true".equals(redisUtil.get("sms:SIGN_UP:Verified:" + signUpRequestDto.getPhoneNumber()))) {
            System.out.println("noRedis.");
            throw new IllegalArgumentException("인증 코드가 존재하지 않습니다.");
        }

        memberRepository.save(signUpRequestDto.toEntity(passwordEncoder));


    }
}
