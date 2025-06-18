package com.pieceofcake.auth_service.member.presentation;

import com.pieceofcake.auth_service.common.entity.BaseResponseEntity;
import com.pieceofcake.auth_service.common.entity.BaseResponseStatus;
import com.pieceofcake.auth_service.member.application.MemberService;
import com.pieceofcake.auth_service.member.dto.in.*;
import com.pieceofcake.auth_service.member.dto.out.CheckEmailResponseDto;
import com.pieceofcake.auth_service.member.vo.in.*;
import com.pieceofcake.auth_service.member.vo.out.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
            @RequestHeader(value = "X-Member-Uuid") String memberUuid
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

    @GetMapping("/find-email")
    public BaseResponseEntity<FindEmailResponseVo> findEmail(
            @ModelAttribute FindEmailRequestVo findEmailRequestVo
    ) {
        return new BaseResponseEntity<>(
                memberService.findEmail(FindEmailRequestDto.from(findEmailRequestVo)).toVo()
        );
    }

    @PostMapping("/reset-password")
    public BaseResponseEntity<Void> resetPassword(
            @RequestBody @Valid ResetPasswordRequestVo resetPasswordRequestVo
    ) {
        memberService.resetPassword(ResetPasswordRequestDto.from(resetPasswordRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.PASSWORD_RESET_SUCCESS);
    }

    @PostMapping("/change-password")
    public BaseResponseEntity<Void> changePassword(
            @RequestHeader(value = "X-Member-Uuid") String memberUuid,
            @RequestBody @Valid ChangePasswordRequestVo changePasswordRequestVo
    ) {
        memberService.changePassword(ChangePasswordRequestDto.of(memberUuid, changePasswordRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.PASSWORD_CHANGE_SUCCESS);
    }

    @PutMapping("/member")
    public BaseResponseEntity<Void> updateMember(
            @RequestHeader(value = "X-Member-Uuid") String memberUuid,
            @RequestBody @Valid UpdateMemberRequestVo updateMemberRequestVo
    ) {
        memberService.updateMember(UpdateMemberRequestDto.of(memberUuid, updateMemberRequestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.MEMBER_UPDATE_SUCCESS);
    }

    @GetMapping("/member")
    public BaseResponseEntity<ReadMemberResponseVo> readMember(
            @RequestHeader(value = "X-Member-Uuid") String memberUuid
    ) {
        ReadMemberRequestVo readMemberRequestVo = ReadMemberRequestVo.builder()
                .memberUuid(memberUuid)
                .build();

        return new BaseResponseEntity<>(
                memberService.readMember(ReadMemberRequestDto.from(readMemberRequestVo)).toVo()
        );
    }

}
