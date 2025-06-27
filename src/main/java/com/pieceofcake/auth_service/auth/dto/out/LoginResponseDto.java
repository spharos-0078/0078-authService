package com.pieceofcake.auth_service.auth.dto.out;

import com.pieceofcake.auth_service.auth.vo.out.LoginResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String memberUuid;

    @Builder
    public LoginResponseDto(
            String accessToken,
            String refreshToken,
            String memberUuid
    ) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.memberUuid = memberUuid;
    }

    public static LoginResponseDto of(String accessToken, String refreshToken, String memberUuid) {
        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .memberUuid(memberUuid)
                .build();
    }

    public LoginResponseVo toVo() {
        return LoginResponseVo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .memberUuid(memberUuid)
                .build();
    }
}
