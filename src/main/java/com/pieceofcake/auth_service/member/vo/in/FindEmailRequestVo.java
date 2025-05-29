package com.pieceofcake.auth_service.member.vo.in;

import lombok.Getter;

@Getter
public class FindEmailRequestVo {

    private String name;
    private String phoneNumber;
    private String birthDate;
}
