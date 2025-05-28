package com.pieceofcake.auth_service.member.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CheckNicknameRequestDto {

    private String nickname;

    @Builder
    public CheckNicknameRequestDto(String nickname) {
        this.nickname = nickname;
    }

    public static CheckNicknameRequestDto of(String nickname) {
        return CheckNicknameRequestDto.builder()
                .nickname(nickname)
                .build();
    }
}
