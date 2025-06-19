package com.pieceofcake.auth_service.auth.dto.in;

import com.pieceofcake.auth_service.auth.vo.in.ResetPasswordRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResetPasswordRequestDto {
    private String email;
    private String phoneNumber;

    @Builder
    public ResetPasswordRequestDto(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static ResetPasswordRequestDto from(ResetPasswordRequestVo resetPasswordRequestVo) {
        return ResetPasswordRequestDto.builder()
                .email(resetPasswordRequestVo.getEmail())
                .phoneNumber(resetPasswordRequestVo.getPhoneNumber())
                .build();
    }
}
