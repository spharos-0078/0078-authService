package com.pieceofcake.auth_service.member.dto.in;

import com.pieceofcake.auth_service.member.dto.enums.SendPhoneCodePurpose;
import com.pieceofcake.auth_service.member.vo.in.SendPhoneCodeRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendPhoneCodeRequestDto {

    private String phoneNumber;
    private SendPhoneCodePurpose purpose;

    @Builder
    public SendPhoneCodeRequestDto(String phoneNumber, SendPhoneCodePurpose purpose) {
        this.phoneNumber = phoneNumber;
        this.purpose = purpose;
    }

    public static SendPhoneCodeRequestDto from(SendPhoneCodeRequestVo sendPhoneCodeRequestVo) {
        return SendPhoneCodeRequestDto.builder()
                .phoneNumber(sendPhoneCodeRequestVo.getPhoneNumber())
                .purpose(SendPhoneCodePurpose.valueOf(sendPhoneCodeRequestVo.getPurpose().toUpperCase()))
                .build();
    }
}
