package com.pieceofcake.auth_service.auth.dto.in;

import com.pieceofcake.auth_service.auth.entity.Auth;
import com.pieceofcake.auth_service.auth.entity.enums.AuthRole;
import com.pieceofcake.auth_service.auth.entity.enums.MemberGender;
import com.pieceofcake.auth_service.auth.entity.enums.MemberStatus;
import com.pieceofcake.auth_service.auth.vo.in.SignUpRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private LocalDateTime birthdate;
    private MemberGender gender;

    @Builder
    public SignUpRequestDto(
            String email,
            String password,
            String name,
            String nickname,
            String phoneNumber,
            LocalDateTime birthdate,
            MemberGender gender
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public Auth toEntity(PasswordEncoder passwordEncoder) {
        return Auth.builder()
                .memberUuid(UUID.randomUUID().toString())
                .email(email)
                .password(passwordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .role(AuthRole.ROLE_USER)       // 일반 사용자 저장
                .status(MemberStatus.ENABLED)
                .build();
    }

    public static SignUpRequestDto from(SignUpRequestVo signUpRequestVo) {
        return SignUpRequestDto.builder()
                .email(signUpRequestVo.getEmail())
                .password(signUpRequestVo.getPassword())
                .name(signUpRequestVo.getName())
                .nickname(signUpRequestVo.getNickname())
                .phoneNumber(signUpRequestVo.getPhoneNumber())
                .birthdate(signUpRequestVo.getBirthdate())
                .gender(signUpRequestVo.getGender())
                .build();
    }

}
