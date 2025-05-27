package com.pieceofcake.auth_service.member.presentation;

import com.pieceofcake.auth_service.member.application.MemberService;
import com.pieceofcake.auth_service.member.dto.in.SignUpRequestDto;
import com.pieceofcake.auth_service.member.vo.in.SignUpRequestVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberAuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public Void signUp(
            @Valid @RequestBody SignUpRequestVo signUpRequestVo
    ) {
        memberService.signUp(SignUpRequestDto.from(signUpRequestVo));
        return null;
    }





}
