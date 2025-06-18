package com.pieceofcake.auth_service.member.dto.out;

import com.pieceofcake.auth_service.member.entity.Member;
import com.pieceofcake.auth_service.member.entity.enums.MemberGender;
import com.pieceofcake.auth_service.member.vo.out.ReadMemberResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReadMemberResponseDto {

    private String email;
    private String name;
    private LocalDateTime birthdate;
    private MemberGender gender;
    private String nickname;
    private String profileImageUrl;

    @Builder
    public ReadMemberResponseDto(
            String email,
            String name,
            LocalDateTime birthdate,
            MemberGender gender,
            String nickname,
            String profileImageUrl
    ) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public static ReadMemberResponseDto from(Member member) {
        return ReadMemberResponseDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .birthdate(member.getBirthdate())
                .gender(member.getGender())
                .nickname(member.getNickname())
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }

    public ReadMemberResponseVo toVo() {
        return ReadMemberResponseVo.builder()
                .email(email)
                .name(name)
                .birthdate(birthdate)
                .gender(gender)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
    }

}
