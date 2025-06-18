package com.pieceofcake.auth_service.common.util;

import com.pieceofcake.auth_service.common.entity.BaseResponseStatus;
import com.pieceofcake.auth_service.common.exception.BaseException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendSmsUtil {

    @Value("${spring.coolsms.sender}")
    private String senderNumber;

    @Value("${spring.coolsms.api-key}")
    private String apiKey;

    @Value("${spring.coolsms.api-secret}")
    private String apiSecret;

    DefaultMessageService messageService;


    @PostConstruct
    public void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }

    public SingleMessageSentResponse sendPhoneCode(String to, String verificationCode){
        Message message = new Message();
        message.setFrom(senderNumber);
        message.setTo(to);
        message.setText("[piece of cake] 본인 확인 인증번호는 " + verificationCode + "입니다.");

        try {
            SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
            // 응답 코드에 따라 예외 처리 (CoolSMS API 응답 코드 체크)
            // http://developers.solapi.com/references/message-status-codes
            if (response.getStatusCode() != null && !response.getStatusCode().equals("2000")) {
                log.info("SMS 전송 실패: {}", response.getStatusCode());
                throw new BaseException(BaseResponseStatus.SMS_SEND_ERROR);
            }
            return response;
        } catch (Exception e) {
            // 네트워크 오류나 기타 예외 처리
            throw new BaseException(BaseResponseStatus.SMS_SEND_FAILED);
        }
    }

    public SingleMessageSentResponse sendNewPassword(String to, String encodedPassword){
        Message message = new Message();
        message.setFrom(senderNumber);
        message.setTo(to);
        message.setText("[piece of cake] 새 비밀번호는 " + encodedPassword + "입니다.");

        try {
            SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
            // 응답 코드에 따라 예외 처리 (CoolSMS API 응답 코드 체크)
            // http://developers.solapi.com/references/message-status-codes
            if (response.getStatusCode() != null && !response.getStatusCode().equals("2000")) {
                log.info("SMS 전송 실패: {}", response.getStatusCode());
                throw new BaseException(BaseResponseStatus.SMS_SEND_ERROR);
            }
            return response;
        } catch (Exception e) {
            // 네트워크 오류나 기타 예외 처리
            throw new BaseException(BaseResponseStatus.SMS_SEND_FAILED);
        }    }



}
