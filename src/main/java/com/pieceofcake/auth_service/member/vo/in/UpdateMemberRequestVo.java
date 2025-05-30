package com.pieceofcake.auth_service.member.vo.in;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateMemberRequestVo {
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    private String nickname;


    private String profileImageUrl;
}
