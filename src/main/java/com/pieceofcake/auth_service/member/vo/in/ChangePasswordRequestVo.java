package com.pieceofcake.auth_service.member.vo.in;

import lombok.Getter;

@Getter
public class ChangePasswordRequestVo {
    private String oldPassword;
    private String newPassword;

    public ChangePasswordRequestVo(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
