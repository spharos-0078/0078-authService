package com.pieceofcake.auth_service.member.application;

import com.pieceofcake.auth_service.member.dto.in.SignUpRequestDto;

public interface MemberService {
    void signUp(SignUpRequestDto signUpRequestDto);
}
