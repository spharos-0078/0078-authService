package com.pieceofcake.auth_service.member.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FindEmailRequestVo {

    private String name;
    private String phoneNumber;
    private String birthdate;

    @Builder
    public FindEmailRequestVo(String name, String phoneNumber, String birthdate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
    }
}
