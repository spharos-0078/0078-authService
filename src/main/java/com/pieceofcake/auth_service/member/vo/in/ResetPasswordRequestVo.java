package com.pieceofcake.auth_service.member.vo.in;

import lombok.Getter;

@Getter
public class ResetPasswordRequestVo {
    private String email;
    private String phoneNumber;
}
