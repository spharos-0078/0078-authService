package com.pieceofcake.auth_service.member.dto.out;

import com.pieceofcake.auth_service.member.vo.out.LoginResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static LoginResponseDto of(String accessToken, String refreshToken) {
        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public LoginResponseVo toVo() {
        return LoginResponseVo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
