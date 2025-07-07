package com.pieceofcake.auth_service.smsverify.presentation;

import com.pieceofcake.auth_service.common.entity.BaseResponseEntity;
import com.pieceofcake.auth_service.common.entity.BaseResponseStatus;
import com.pieceofcake.auth_service.smsverify.application.PhoneService;
import com.pieceofcake.auth_service.smsverify.dto.in.SendPhoneCodeRequestDto;
import com.pieceofcake.auth_service.smsverify.dto.in.VerifyPhoneCodeRequestDto;
import com.pieceofcake.auth_service.smsverify.vo.in.SendPhoneCodeRequestVo;
import com.pieceofcake.auth_service.smsverify.vo.in.VerifyPhoneCodeRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/phone")
@RequiredArgsConstructor
@Tag(name = "Phone Authentication", description = "휴대폰 인증 관련 API")
public class PhoneAuthController {

    private final PhoneService phoneService;

    @PostMapping("/send-code")
    @Operation(
        summary = "휴대폰 인증 코드 전송",
        description = "지정된 휴대폰 번호로 인증 코드를 전송합니다. " +
                     "purpose는 다음 중 하나여야 합니다: SIGN_UP, FIND_EMAIL, RESET_PASSWORD, VERIFY_PHONE_NUMBER, CHANGE_PHONE_NUMBER"
    )
    public BaseResponseEntity<Void> sendPhoneCode(@RequestBody SendPhoneCodeRequestVo sendPhoneCodeRequestVo
    ) {
        phoneService.sendPhoneCode(SendPhoneCodeRequestDto.from(sendPhoneCodeRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SMS_CODE_SUCCESS);
    }

    @PostMapping("/verify")
    @Operation(
        summary = "휴대폰 인증 코드 검증",
        description = "전송된 인증 코드를 검증합니다. " +
                     "purpose는 다음 중 하나여야 합니다: SIGN_UP, FIND_EMAIL, RESET_PASSWORD, VERIFY_PHONE_NUMBER, CHANGE_PHONE_NUMBER"
    )
    public BaseResponseEntity<Void> verifyPhoneCode(
            @RequestBody VerifyPhoneCodeRequestVo verifyPhoneCodeRequestVo
    ) {
        phoneService.verifyPhoneCode(VerifyPhoneCodeRequestDto.from(verifyPhoneCodeRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SMS_CODE_VERIFICATION_SUCCESS);
    }
}
