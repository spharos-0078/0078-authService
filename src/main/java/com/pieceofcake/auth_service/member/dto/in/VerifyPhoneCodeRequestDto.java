package com.pieceofcake.auth_service.member.dto.in;

import com.pieceofcake.auth_service.member.vo.in.VerifyPhoneCodeRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerifyPhoneCodeRequestDto {
    private String phoneNumber;
    private String verificationCode;

    @Builder
    public VerifyPhoneCodeRequestDto(String phoneNumber, String verificationCode) {
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
    }

    public static VerifyPhoneCodeRequestDto from(VerifyPhoneCodeRequestVo verifyPhoneCodeRequestVo) {
        return new VerifyPhoneCodeRequestDto(
                verifyPhoneCodeRequestVo.getPhoneNumber(),
                verifyPhoneCodeRequestVo.getVerificationCode()
        );
    }
}
