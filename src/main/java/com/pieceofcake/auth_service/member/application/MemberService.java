package com.pieceofcake.auth_service.member.application;

import com.pieceofcake.auth_service.member.dto.in.*;
import com.pieceofcake.auth_service.member.dto.out.CheckEmailResponseDto;
import com.pieceofcake.auth_service.member.dto.out.CheckNicknameResponseDto;
import com.pieceofcake.auth_service.member.dto.out.FindEmailResponseDto;
import com.pieceofcake.auth_service.member.dto.out.LoginResponseDto;

public interface MemberService {
    void signUp(SignUpRequestDto signUpRequestDto);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    void logout(String memberUuid);
    CheckEmailResponseDto checkEmail(CheckEmailRequestDto checkEmailRequestDto);
    CheckNicknameResponseDto checkNickname(CheckNicknameRequestDto checkNicknameRequestDto);
    FindEmailResponseDto findEmail(FindEmailRequestDto findEmailRequestDto);
}
