package com.pieceofcake.auth_service.member.application;

import com.pieceofcake.auth_service.member.dto.in.SendPhoneCodeRequestDto;
import com.pieceofcake.auth_service.member.dto.in.VerifyPhoneCodeRequestDto;

public interface PhoneService {
    void sendPhoneCode(SendPhoneCodeRequestDto sendPhoneCodeRequestDto);
    void verifyPhoneCode(VerifyPhoneCodeRequestDto verifyPhoneCodeRequestDto);
    void sendNewPassword(String phoneNumber, String newPassword);
}
