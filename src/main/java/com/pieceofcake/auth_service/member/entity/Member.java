package com.pieceofcake.auth_service.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pieceofcake.auth_service.common.entity.BaseEntity;
import com.pieceofcake.auth_service.member.entity.enums.MemberGender;
import com.pieceofcake.auth_service.member.entity.enums.MemberRole;
import com.pieceofcake.auth_service.member.entity.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "member_uuid", nullable = false, unique = true, updatable = false)
    private String memberUuid;

    @Column(name = "email", nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private MemberGender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MemberStatus status;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "role", nullable = false)
    private MemberRole role = MemberRole.ROLE_USER; // 기본값은 ROLE_USER로 설정


    @Builder
    public Member(
            Long id,
            String memberUuid,
            String email,
            String password,
            String name,
            String phoneNumber,
            LocalDateTime birthdate,
            MemberGender gender,
            MemberStatus status,
            String nickname,
            String profileImageUrl,
            MemberRole role
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.gender = gender;
        this.status = status;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.role = (role == null) ? MemberRole.ROLE_USER : role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.memberUuid;
    }
}
