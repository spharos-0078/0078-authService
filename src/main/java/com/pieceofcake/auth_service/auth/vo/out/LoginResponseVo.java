package com.pieceofcake.auth_service.auth.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseVo {
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponseVo(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
