package com.pieceofcake.auth_service.member.vo.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class FindEmailRequestVo {

    private String name;
    private String phoneNumber;
    private String birthdate;

}
