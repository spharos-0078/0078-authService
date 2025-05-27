package com.pieceofcake.auth_service.member.application;

import com.pieceofcake.auth_service.common.util.SendPhoneCodeUtil;
import com.pieceofcake.auth_service.common.util.RedisUtil;
import com.pieceofcake.auth_service.member.dto.in.SendPhoneCodeRequestDto;
import com.pieceofcake.auth_service.member.dto.in.VerifyPhoneCodeRequestDto;
import com.pieceofcake.auth_service.member.infrastructure.SendPhoneCodeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService{

    private final RedisUtil redisUtil;
    private final SendPhoneCodeUtil sendPhoneCodeUtil;
    private final SendPhoneCodeDao sendPhoneCodeDao;

    @Override
    public void sendPhoneCode(SendPhoneCodeRequestDto sendPhoneCodeRequestDto) {
        String phoneNumber = sendPhoneCodeRequestDto.getPhoneNumber();
        int randomNumber = (int) (Math.random() * 900000) + 100000; // 6자리 랜덤 숫자 생성
        String certificationNumber = String.valueOf(randomNumber);
        sendPhoneCodeUtil.sendSms(phoneNumber, certificationNumber);
        sendPhoneCodeDao.createSmsCertification(phoneNumber, certificationNumber);
    }

    @Override
    public void verifyPhoneCode(VerifyPhoneCodeRequestDto verifyPhoneCodeRequestDto) {
        String phoneNumber = verifyPhoneCodeRequestDto.getPhoneNumber();
        String verificationCode = verifyPhoneCodeRequestDto.getVerificationCode();

        if (!sendPhoneCodeDao.hasKey(phoneNumber)) {
            throw new IllegalArgumentException("인증 코드가 존재하지 않습니다.");
        }

        String redisStoredCode = sendPhoneCodeDao.getSmsCertification(phoneNumber);
        if (!redisStoredCode.equals(verificationCode)) {
            throw new IllegalArgumentException("인증 코드가 일치하지 않습니다.");
        }

        sendPhoneCodeDao.removeSmsCertification(phoneNumber);
    }
}
