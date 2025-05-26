package com.pieceofcake.auth_service.member.application;

import com.pieceofcake.auth_service.member.dto.in.SendPhoneCodeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService{

    @Override
    public void sendPhoneCode(SendPhoneCodeRequestDto sendPhoneCodeRequestDto) {
        System.out.println("Sending phone code for: " + sendPhoneCodeRequestDto.getPhoneNumber());
    }
}
