package com.pieceofcake.auth_service.member.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckEmailResponseVo {
    public boolean available;

    @Builder
    public CheckEmailResponseVo(boolean available) {
        this.available = available;
    }
}
