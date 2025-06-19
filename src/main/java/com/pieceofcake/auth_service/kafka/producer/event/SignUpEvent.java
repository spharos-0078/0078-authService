package com.pieceofcake.auth_service.kafka.producer.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pieceofcake.auth_service.auth.entity.enums.MemberGender;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpEvent {
    private String memberUuid;
    private String email;
    private String name;
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime birthdate;
    private MemberGender gender;
    private String nickname;
}
