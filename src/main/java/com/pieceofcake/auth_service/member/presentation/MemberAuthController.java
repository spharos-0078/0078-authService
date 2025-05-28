package com.pieceofcake.auth_service.member.presentation;

import com.pieceofcake.auth_service.common.entity.BaseResponseEntity;
import com.pieceofcake.auth_service.common.entity.BaseResponseStatus;
import com.pieceofcake.auth_service.member.application.MemberService;
import com.pieceofcake.auth_service.member.dto.in.CheckEmailRequestDto;
import com.pieceofcake.auth_service.member.dto.in.CheckNicknameRequestDto;
import com.pieceofcake.auth_service.member.dto.in.LoginRequestDto;
import com.pieceofcake.auth_service.member.dto.in.SignUpRequestDto;
import com.pieceofcake.auth_service.member.dto.out.CheckEmailResponseDto;
import com.pieceofcake.auth_service.member.vo.in.LoginRequestVo;
import com.pieceofcake.auth_service.member.vo.in.SignUpRequestVo;
import com.pieceofcake.auth_service.member.vo.out.CheckEmailResponseVo;
import com.pieceofcake.auth_service.member.vo.out.CheckNicknameResponseVo;
import com.pieceofcake.auth_service.member.vo.out.LoginResponseVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberAuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public BaseResponseEntity<Void> signUp(
            @Valid @RequestBody SignUpRequestVo signUpRequestVo
    ) {
        memberService.signUp(SignUpRequestDto.from(signUpRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SIGN_UP_SUCCESS);
    }

    @PostMapping("/login")
    public BaseResponseEntity<LoginResponseVo> login(
            @Valid @RequestBody LoginRequestVo loginRequestVo
    ) {
        return new BaseResponseEntity<LoginResponseVo> (
                memberService.login(LoginRequestDto.from(loginRequestVo)).toVo()
        );
    }

    @PostMapping("/logout")
    public BaseResponseEntity<Void> logout(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader(value = "X-Member-Uuid", required = false) String memberUuid
    ) {
        memberService.logout(memberUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.LOGOUT_SUCCESS);
    }

    @GetMapping("/check-email")
    public BaseResponseEntity<CheckEmailResponseVo> checkEmail(
            @RequestParam("email") String email
    ) {
        return new BaseResponseEntity<CheckEmailResponseVo> (
                memberService.checkEmail(CheckEmailRequestDto.of(email)).toVo()
        );
    }

    @GetMapping("/check-nickname")
    public BaseResponseEntity<CheckNicknameResponseVo> checkNickname(
            @RequestParam("nickname") String nickname
    ) {
        return new BaseResponseEntity<CheckNicknameResponseVo>(
                memberService.checkNickname(CheckNicknameRequestDto.of(nickname)).toVo()
        );
    }



}
