package com.pieceofcake.auth_service.smsverify.application;

import com.pieceofcake.auth_service.common.entity.BaseResponseStatus;
import com.pieceofcake.auth_service.common.exception.BaseException;
import com.pieceofcake.auth_service.common.util.SendSmsUtil;
import com.pieceofcake.auth_service.common.util.RedisUtil;
import com.pieceofcake.auth_service.smsverify.dto.in.SendPhoneCodeRequestDto;
import com.pieceofcake.auth_service.smsverify.dto.in.VerifyPhoneCodeRequestDto;
import com.pieceofcake.auth_service.smsverify.infrastructure.SendPhoneCodeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService{

    private final RedisUtil redisUtil;
    private final SendSmsUtil sendSmsUtil;
    private final SendPhoneCodeDao sendPhoneCodeDao;

    @Override
    public void sendPhoneCode(SendPhoneCodeRequestDto sendPhoneCodeRequestDto) {
        String phoneNumber = sendPhoneCodeRequestDto.getPhoneNumber();
        String purpose = sendPhoneCodeRequestDto.getPurpose().name();
        int randomNumber = (int) (Math.random() * 900000) + 100000; // 6자리 랜덤 숫자 생성
        String certificationNumber = String.valueOf(randomNumber);
        sendSmsUtil.sendPhoneCode(phoneNumber, certificationNumber);
        sendPhoneCodeDao.createSmsCertification(phoneNumber, certificationNumber, purpose);
    }

    @Override
    public void verifyPhoneCode(VerifyPhoneCodeRequestDto verifyPhoneCodeRequestDto) {
        String phoneNumber = verifyPhoneCodeRequestDto.getPhoneNumber();
        String purpose = verifyPhoneCodeRequestDto.getPurpose().name();
        String verificationCode = verifyPhoneCodeRequestDto.getVerificationCode();

        if (!sendPhoneCodeDao.hasKey(phoneNumber, purpose)) {
            throw new BaseException(BaseResponseStatus.SMS_CERTIFICATION_NOT_FOUND);
        }

        String redisStoredCode = sendPhoneCodeDao.getSmsCertification(phoneNumber, purpose);
        if (!redisStoredCode.equals(verificationCode)) {
            throw new BaseException(BaseResponseStatus.SMS_VERIFICATION_FAILED);
        }

        sendPhoneCodeDao.createSmsVerifed(phoneNumber, purpose);

        sendPhoneCodeDao.removeSmsCertification(phoneNumber, purpose);
    }

    @Override
    public void sendNewPassword(String phoneNumber, String newPassword) {
        sendSmsUtil.sendNewPassword(phoneNumber, newPassword);
    }
}
