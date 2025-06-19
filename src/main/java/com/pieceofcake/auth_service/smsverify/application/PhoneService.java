package com.pieceofcake.auth_service.smsverify.application;

import com.pieceofcake.auth_service.smsverify.dto.in.SendPhoneCodeRequestDto;
import com.pieceofcake.auth_service.smsverify.dto.in.VerifyPhoneCodeRequestDto;

public interface PhoneService {
    void sendPhoneCode(SendPhoneCodeRequestDto sendPhoneCodeRequestDto);
    void verifyPhoneCode(VerifyPhoneCodeRequestDto verifyPhoneCodeRequestDto);
    void sendNewPassword(String phoneNumber, String newPassword);
}
