package com.pieceofcake.auth_service.member.presentation;

import com.pieceofcake.auth_service.member.application.PhoneService;
import com.pieceofcake.auth_service.member.dto.in.SendPhoneCodeRequestDto;
import com.pieceofcake.auth_service.member.dto.in.VerifyPhoneCodeRequestDto;
import com.pieceofcake.auth_service.member.vo.in.SendPhoneCodeRequestVo;
import com.pieceofcake.auth_service.member.vo.in.VerifyPhoneCodeRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PhoneAuthController {

    private final PhoneService phoneService;

    @PostMapping("/phone/send-code")
    public ResponseEntity<Void> sendPhoneCode(@RequestBody SendPhoneCodeRequestVo sendPhoneCodeRequestVo
    ) {
        phoneService.sendPhoneCode(SendPhoneCodeRequestDto.from(sendPhoneCodeRequestVo));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/phone/verify")
    public ResponseEntity<Void> verifyPhoneCode(
            @RequestBody VerifyPhoneCodeRequestVo verifyPhoneCodeRequestVo
    ) {
        phoneService.verifyPhoneCode(VerifyPhoneCodeRequestDto.from(verifyPhoneCodeRequestVo));
        return ResponseEntity.ok().build();
    }
}
