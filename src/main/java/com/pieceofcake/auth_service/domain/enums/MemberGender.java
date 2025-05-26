package com.pieceofcake.auth_service.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberGender {
    MALE("남성"),
    FEMALE("여성");

    @JsonValue
    private final String label;
}
