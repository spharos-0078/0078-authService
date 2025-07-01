package com.pieceofcake.auth_service.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pieceofcake.auth_service.common.entity.BaseEntity;
import com.pieceofcake.auth_service.auth.entity.enums.AuthRole;
import com.pieceofcake.auth_service.auth.entity.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Table(name = "auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Auth extends BaseEntity implements UserDetails {

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

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private AuthRole role; // 기본값은 ROLE_USER로 설정

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MemberStatus status;


    @Builder
    public Auth(
            String memberUuid,
            String email,
            String password,
            String phoneNumber,
            AuthRole role,
            MemberStatus status
    ) {
        this.memberUuid = memberUuid;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = status;
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
