package com.pieceofcake.auth_service.member.dto.in;

import com.pieceofcake.auth_service.member.dto.enums.SendPhoneCodePurpose;
import com.pieceofcake.auth_service.member.vo.in.VerifyPhoneCodeRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerifyPhoneCodeRequestDto {
    private String phoneNumber;
    private SendPhoneCodePurpose purpose;
    private String verificationCode;

    @Builder
    public VerifyPhoneCodeRequestDto(
            String phoneNumber,
            SendPhoneCodePurpose purpose,
            String verificationCode
    ) {
        this.phoneNumber = phoneNumber;
        this.purpose = purpose;
        this.verificationCode = verificationCode;
    }

    public static VerifyPhoneCodeRequestDto from(VerifyPhoneCodeRequestVo verifyPhoneCodeRequestVo) {
        return new VerifyPhoneCodeRequestDto(
                verifyPhoneCodeRequestVo.getPhoneNumber(),
                SendPhoneCodePurpose.valueOf(verifyPhoneCodeRequestVo.getPurpose()),
                verifyPhoneCodeRequestVo.getVerificationCode()
        );
    }
}
