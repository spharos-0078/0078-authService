package com.pieceofcake.auth_service.auth.presentation;

import com.pieceofcake.auth_service.common.entity.BaseResponseEntity;
import com.pieceofcake.auth_service.common.entity.BaseResponseStatus;
import com.pieceofcake.auth_service.auth.application.AuthService;
import com.pieceofcake.auth_service.auth.dto.in.*;
import com.pieceofcake.auth_service.auth.vo.in.*;
import com.pieceofcake.auth_service.auth.vo.out.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "인증 관련 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(
        summary = "회원가입",
        description = "새로운 사용자를 등록합니다."
    )
    public BaseResponseEntity<Void> signUp(
            @Valid @RequestBody SignUpRequestVo signUpRequestVo
    ) {
        authService.signUp(SignUpRequestDto.from(signUpRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SIGN_UP_SUCCESS);
    }

    @PostMapping("/login")
    @Operation(
        summary = "로그인",
        description = "사용자 인증을 통해 로그인합니다."
    )
    public BaseResponseEntity<LoginResponseVo> login(
            @Valid @RequestBody LoginRequestVo loginRequestVo
    ) {
        return new BaseResponseEntity<LoginResponseVo> (
                authService.login(LoginRequestDto.from(loginRequestVo)).toVo()
        );
    }

    @PostMapping("/logout")
    @Operation(
        summary = "로그아웃",
        description = "사용자 세션을 종료합니다."
    )
    public BaseResponseEntity<Void> logout(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader(value = "X-Member-Uuid") String memberUuid
    ) {
        authService.logout(memberUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.LOGOUT_SUCCESS);
    }

    @PostMapping("/reset-password")
    @Operation(
        summary = "비밀번호 재설정",
        description = "사용자의 비밀번호를 재설정합니다."
    )
    public BaseResponseEntity<Void> resetPassword(
            @RequestBody @Valid ResetPasswordRequestVo resetPasswordRequestVo
    ) {
        authService.resetPassword(ResetPasswordRequestDto.from(resetPasswordRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.PASSWORD_RESET_SUCCESS);
    }

    @PostMapping("/change-password")
    @Operation(
        summary = "비밀번호 변경",
        description = "로그인한 사용자의 비밀번호를 변경합니다."
    )
    public BaseResponseEntity<Void> changePassword(
            @RequestHeader(value = "X-Member-Uuid") String memberUuid,
            @RequestBody @Valid ChangePasswordRequestVo changePasswordRequestVo
    ) {
        authService.changePassword(ChangePasswordRequestDto.of(memberUuid, changePasswordRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.PASSWORD_CHANGE_SUCCESS);
    }
}
