package com.pieceofcake.auth_service.smsverify.vo.in;

import lombok.Getter;

@Getter
public class SendPhoneCodeRequestVo {
    private String phoneNumber;
    private String purpose;
}
