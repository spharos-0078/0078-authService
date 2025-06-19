package com.pieceofcake.auth_service.auth.vo.in;

import lombok.Getter;

@Getter
public class LoginRequestVo {
    private String email;
    private String password;
}
