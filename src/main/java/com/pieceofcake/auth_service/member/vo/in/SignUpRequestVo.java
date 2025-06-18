package com.pieceofcake.auth_service.member.vo.in;

import com.pieceofcake.auth_service.member.entity.enums.MemberGender;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class SignUpRequestVo {

    @NotBlank
    @Size(min = 10, max = 30, message = "이메일은 10자 이상 30자 이하로 입력해주세요.")
    @Email(message = "올바른 이메일 형식을 입력해주세요.")
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{10,20}$",
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함하여 10~20자여야 합니다."
    )
    private String password;

    @NotBlank
    @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하로 입력해주세요.")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자여야 합니다.")
    private String nickname;

    @NotBlank
    @Pattern(
            regexp = "^010\\d{4}\\d{4}$",
            message = "전화번호는 010xxxxxxxx 형식이어야 합니다."
    )
    private String phoneNumber;

    @NotNull(message = "생년월일을 입력해주세요.")
    @Past(message = "생년월일은 과거 날짜여야 합니다.")
    private LocalDateTime birthdate;

    @NotNull(message = "성별을 선택해주세요.")
    private MemberGender gender;
}
