package com.pieceofcake.auth_service.member.application;

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

        memberRepository.save(signUpRequestDto.toEntity(passwordEncoder));


    }
}
