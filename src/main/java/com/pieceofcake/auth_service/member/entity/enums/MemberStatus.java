package com.pieceofcake.auth_service.member.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {
    ENABLED("활성"),
    DISABLED("비활성"),
    BLACKED("블랙리스트"),
    DELETED("삭제됨");

    @JsonValue
    private final String label;
}
