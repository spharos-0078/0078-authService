package com.pieceofcake.auth_service.member.dto.in;

import com.pieceofcake.auth_service.member.vo.in.ReadMemberRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadMemberRequestDto {
    private String memberUuid;

    @Builder
    public ReadMemberRequestDto(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public static ReadMemberRequestDto from(ReadMemberRequestVo readMemberRequestVo) {
        return ReadMemberRequestDto.builder()
                .memberUuid(readMemberRequestVo.getMemberUuid())
                .build();
    }
}
