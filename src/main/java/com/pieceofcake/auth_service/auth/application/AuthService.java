package com.pieceofcake.auth_service.auth.application;

import com.pieceofcake.auth_service.auth.dto.in.*;
import com.pieceofcake.auth_service.auth.dto.out.*;

public interface AuthService {
    void signUp(SignUpRequestDto signUpRequestDto);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    void logout(String memberUuid);
    void resetPassword(ResetPasswordRequestDto resetPasswordRequestDto);
    void changePassword(ChangePasswordRequestDto changePasswordRequestDto);
}
