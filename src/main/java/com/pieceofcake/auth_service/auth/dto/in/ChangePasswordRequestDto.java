package com.pieceofcake.auth_service.auth.dto.in;

import com.pieceofcake.auth_service.auth.vo.in.ChangePasswordRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangePasswordRequestDto {
    private String memberUuid;
    private String oldPassword;
    private String newPassword;

    @Builder
    public ChangePasswordRequestDto(String memberUuid, String oldPassword, String newPassword) {
        this.memberUuid = memberUuid;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public static ChangePasswordRequestDto of(String memberUuid, ChangePasswordRequestVo changePasswordRequestVo) {
        return ChangePasswordRequestDto.builder()
                .memberUuid(memberUuid)
                .oldPassword(changePasswordRequestVo.getOldPassword())
                .newPassword(changePasswordRequestVo.getNewPassword())
                .build();
    }
}
