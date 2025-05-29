package com.pieceofcake.auth_service.member.dto.out;

import com.pieceofcake.auth_service.member.entity.Member;
import com.pieceofcake.auth_service.member.vo.out.FindEmailResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindEmailResponseDto {
    private String email;

    @Builder
    public FindEmailResponseDto(String email) {
        this.email = email;
    }

    public static FindEmailResponseDto from(Member member) {
        return FindEmailResponseDto.builder()
                .email(member.getEmail())
                .build();
    }

    public FindEmailResponseVo toVo() {
        return FindEmailResponseVo.builder()
                .email(email)
                .build();
    }
}
