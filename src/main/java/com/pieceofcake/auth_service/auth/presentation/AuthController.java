package com.pieceofcake.auth_service.auth.presentation;

import com.pieceofcake.auth_service.common.entity.BaseResponseEntity;
import com.pieceofcake.auth_service.common.entity.BaseResponseStatus;
import com.pieceofcake.auth_service.auth.application.AuthService;
import com.pieceofcake.auth_service.auth.dto.in.*;
import com.pieceofcake.auth_service.auth.vo.in.*;
import com.pieceofcake.auth_service.auth.vo.out.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public BaseResponseEntity<Void> signUp(
            @Valid @RequestBody SignUpRequestVo signUpRequestVo
    ) {
        authService.signUp(SignUpRequestDto.from(signUpRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SIGN_UP_SUCCESS);
    }

    @PostMapping("/login")
    public BaseResponseEntity<LoginResponseVo> login(
            @Valid @RequestBody LoginRequestVo loginRequestVo
    ) {
        return new BaseResponseEntity<LoginResponseVo> (
                authService.login(LoginRequestDto.from(loginRequestVo)).toVo()
        );
    }

    @PostMapping("/logout")
    public BaseResponseEntity<Void> logout(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader(value = "X-Member-Uuid") String memberUuid
    ) {
        authService.logout(memberUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.LOGOUT_SUCCESS);
    }

    @PostMapping("/reset-password")
    public BaseResponseEntity<Void> resetPassword(
            @RequestBody @Valid ResetPasswordRequestVo resetPasswordRequestVo
    ) {
        authService.resetPassword(ResetPasswordRequestDto.from(resetPasswordRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.PASSWORD_RESET_SUCCESS);
    }

    @PostMapping("/change-password")
    public BaseResponseEntity<Void> changePassword(
            @RequestHeader(value = "X-Member-Uuid") String memberUuid,
            @RequestBody @Valid ChangePasswordRequestVo changePasswordRequestVo
    ) {
        authService.changePassword(ChangePasswordRequestDto.of(memberUuid, changePasswordRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.PASSWORD_CHANGE_SUCCESS);
    }
}
