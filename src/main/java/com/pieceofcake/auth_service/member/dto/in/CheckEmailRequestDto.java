package com.pieceofcake.auth_service.member.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CheckEmailRequestDto {
    private String email;

    @Builder
    public CheckEmailRequestDto(String email) {
        this.email = email;
    }

    public static CheckEmailRequestDto of(String email) {
        return CheckEmailRequestDto.builder()
                .email(email)
                .build();
    }
}
