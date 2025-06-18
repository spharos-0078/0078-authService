package com.pieceofcake.auth_service.member.application;

import com.pieceofcake.auth_service.member.dto.in.*;
import com.pieceofcake.auth_service.member.dto.out.*;

public interface MemberService {
    void signUp(SignUpRequestDto signUpRequestDto);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    void logout(String memberUuid);
    CheckEmailResponseDto checkEmail(CheckEmailRequestDto checkEmailRequestDto);
    CheckNicknameResponseDto checkNickname(CheckNicknameRequestDto checkNicknameRequestDto);
    FindEmailResponseDto findEmail(FindEmailRequestDto findEmailRequestDto);
    void resetPassword(ResetPasswordRequestDto resetPasswordRequestDto);
    void changePassword(ChangePasswordRequestDto changePasswordRequestDto);
    void updateMember(UpdateMemberRequestDto updateMemberRequestDto);
    ReadMemberResponseDto readMember(ReadMemberRequestDto readMemberRequestDto);
}
