package com.pieceofcake.auth_service.member.vo.out;

import com.pieceofcake.auth_service.member.dto.out.FindEmailResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FindEmailResponseVo {
    private String email;

    @Builder
    public FindEmailResponseVo(String email) {
        this.email = email;
    }
}
