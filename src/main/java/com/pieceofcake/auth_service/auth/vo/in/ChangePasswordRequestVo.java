package com.pieceofcake.auth_service.auth.vo.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ChangePasswordRequestVo {
    @NotBlank
    private String oldPassword;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{10,20}$",
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함하여 10~20자여야 합니다."
    )
    private String newPassword;

    public ChangePasswordRequestVo(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
