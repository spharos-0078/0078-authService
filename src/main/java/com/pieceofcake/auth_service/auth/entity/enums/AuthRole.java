package com.pieceofcake.auth_service.auth.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthRole {

    ROLE_ADMIN("관리자"),
    ROLE_SUPER_ADMIN("슈퍼 관리자"),
    ROLE_USER("일반 사용자");

    private final String label;
}
