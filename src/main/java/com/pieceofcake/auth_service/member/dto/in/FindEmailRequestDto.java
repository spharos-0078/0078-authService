package com.pieceofcake.auth_service.member.dto.in;

import com.pieceofcake.auth_service.member.dto.out.FindEmailResponseDto;
import com.pieceofcake.auth_service.member.vo.in.FindEmailRequestVo;
import com.pieceofcake.auth_service.member.vo.out.FindEmailResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
public class FindEmailRequestDto {

    private String name;
    private String phoneNumber;
    private String birthdate;

    @Builder
    public FindEmailRequestDto(String name, String phoneNumber, String birthdate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
    }

    public static FindEmailRequestDto from(FindEmailRequestVo findEmailRequestVo) {
        log.info("### Converting FindEmailRequestVo to FindEmailRequestDto: {} {} {}", findEmailRequestVo.getName(),
                findEmailRequestVo.getPhoneNumber(), findEmailRequestVo.getBirthdate());
        return FindEmailRequestDto.builder()
                .name(findEmailRequestVo.getName())
                .phoneNumber(findEmailRequestVo.getPhoneNumber())
                .birthdate(findEmailRequestVo.getBirthdate())
                .build();
    }

    public FindEmailResponseVo toVo(FindEmailResponseDto findEmailResponseDto) {
        return FindEmailResponseVo.builder()
                .email(findEmailResponseDto.getEmail())
                .build();
    }
}
