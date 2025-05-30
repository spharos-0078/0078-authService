package com.pieceofcake.auth_service.member.dto.in;

import com.pieceofcake.auth_service.member.entity.Member;
import com.pieceofcake.auth_service.member.vo.in.UpdateMemberRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMemberRequestDto {
    public String memberUuid;
    public String nickname;
    public String profileImageUrl;

    @Builder
    public UpdateMemberRequestDto(String memberUuid, String nickname, String profileImageUrl) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public Member updateEntity(Member member) {
        return Member.builder()
                .id(member.getId())
                .memberUuid(member.getMemberUuid())
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .birthdate(member.getBirthdate())
                .gender(member.getGender())
                .status(member.getStatus())
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .role(member.getRole())
                .build();
    }

    public static UpdateMemberRequestDto of(String memberUuid, UpdateMemberRequestVo updateMemberRequestVo) {
        return UpdateMemberRequestDto.builder()
                .memberUuid(memberUuid)
                .nickname(updateMemberRequestVo.getNickname())
                .profileImageUrl(updateMemberRequestVo.getProfileImageUrl())
                .build();
    }
}
