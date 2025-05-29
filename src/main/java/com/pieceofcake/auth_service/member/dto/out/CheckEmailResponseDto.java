package com.pieceofcake.auth_service.member.dto.out;

import com.pieceofcake.auth_service.member.vo.out.CheckEmailResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CheckEmailResponseDto {

    private boolean available;

    @Builder
    public CheckEmailResponseDto(boolean available) {
        this.available = available;
    }


    public static CheckEmailResponseDto of(boolean available) {
        return CheckEmailResponseDto.builder()
                .available(available)
                .build();
    }

    public CheckEmailResponseVo toVo() {
        return CheckEmailResponseVo.builder()
                .available(available)
                .build();
    }

}
