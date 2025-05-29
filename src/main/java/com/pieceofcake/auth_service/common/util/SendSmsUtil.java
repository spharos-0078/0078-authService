package com.pieceofcake.auth_service.common.util;

import jakarta.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

        return this.messageService.sendOne(new SingleMessageSendingRequest(message));
    }

    public SingleMessageSentResponse sendNewPassword(String to, String encodedPassword){
        Message message = new Message();
        message.setFrom(senderNumber);
        message.setTo(to);
        message.setText("[piece of cake] 새 비밀번호는 " + encodedPassword + "입니다.");

        return this.messageService.sendOne(new SingleMessageSendingRequest(message));
    }



}
