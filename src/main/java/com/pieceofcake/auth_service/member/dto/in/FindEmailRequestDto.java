package com.pieceofcake.auth_service.member.dto.in;

import com.pieceofcake.auth_service.member.dto.out.FindEmailResponseDto;
import com.pieceofcake.auth_service.member.vo.in.FindEmailRequestVo;
import com.pieceofcake.auth_service.member.vo.out.FindEmailResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindEmailRequestDto {

    private String name;
    private String phoneNumber;
    private String birthDate;

    @Builder
    public FindEmailRequestDto(String name, String phoneNumber, String birthDate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public static FindEmailRequestDto from(FindEmailRequestVo findEmailRequestVo) {
        return FindEmailRequestDto.builder()
                .name(findEmailRequestVo.getName())
                .phoneNumber(findEmailRequestVo.getPhoneNumber())
                .birthDate(findEmailRequestVo.getBirthDate())
                .build();
    }

    public FindEmailResponseVo toVo(FindEmailResponseDto findEmailResponseDto) {
        return FindEmailResponseVo.builder()
                .email(findEmailResponseDto.getEmail())
                .build();
    }
}
