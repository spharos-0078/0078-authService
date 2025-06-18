package com.pieceofcake.auth_service.member.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadMemberRequestVo {
    private String memberUuid;

    @Builder
    public ReadMemberRequestVo(String memberUuid) {
        this.memberUuid = memberUuid;
    }
}
