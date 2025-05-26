package com.pieceofcake.auth_service.member.application;

import com.pieceofcake.auth_service.member.dto.in.SendPhoneCodeRequestDto;

public interface PhoneService {
    void sendPhoneCode(SendPhoneCodeRequestDto sendPhoneCodeRequestDto);
}
