package com.pieceofcake.auth_service.auth.dto.in;

import com.pieceofcake.auth_service.auth.vo.in.LoginRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    private String email;
    private String password;

    @Builder
    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static LoginRequestDto from(LoginRequestVo loginRequestVo) {
        return LoginRequestDto.builder()
                .email(loginRequestVo.getEmail())
                .password(loginRequestVo.getPassword())
                .build();
    }
}
