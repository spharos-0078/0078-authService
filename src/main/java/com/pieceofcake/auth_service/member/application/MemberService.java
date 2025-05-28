package com.pieceofcake.auth_service.member.application;

import com.pieceofcake.auth_service.member.dto.in.CheckEmailRequestDto;
import com.pieceofcake.auth_service.member.dto.in.LoginRequestDto;
import com.pieceofcake.auth_service.member.dto.in.SignUpRequestDto;
import com.pieceofcake.auth_service.member.dto.out.CheckEmailResponseDto;
import com.pieceofcake.auth_service.member.dto.out.LoginResponseDto;

public interface MemberService {
    void signUp(SignUpRequestDto signUpRequestDto);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    void logout(String memberUuid);
    CheckEmailResponseDto checkEmail(CheckEmailRequestDto checkEmailRequestDto);
}
