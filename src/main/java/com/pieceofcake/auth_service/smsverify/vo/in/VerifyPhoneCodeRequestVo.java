package com.pieceofcake.auth_service.smsverify.vo.in;

import lombok.Getter;

@Getter
public class VerifyPhoneCodeRequestVo {

    private String phoneNumber;
    private String purpose;
    private String verificationCode;
}
