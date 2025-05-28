package com.pieceofcake.auth_service.member.dto.out;

import com.pieceofcake.auth_service.member.vo.out.CheckNicknameResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckNicknameResponseDto {
    private boolean available;

    @Builder
    public CheckNicknameResponseDto(boolean available) {
        this.available = available;
    }

    public static CheckNicknameResponseDto of(boolean available) {
        return new CheckNicknameResponseDto(available);
    }

    public CheckNicknameResponseVo toVo() {
        return CheckNicknameResponseVo.builder()
                .available(available)
                .build();
    }
}
