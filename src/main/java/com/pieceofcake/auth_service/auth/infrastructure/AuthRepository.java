package com.pieceofcake.auth_service.auth.infrastructure;

import com.pieceofcake.auth_service.auth.entity.Auth;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {

    /**
     *
     */
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Auth> findByMemberUuid(String memberUuid);

    Optional<Auth> findByEmail(String email);

    @Modifying
    @Transactional  // 이 어노테이션 꼭 필요함!
    @Query("UPDATE Auth u SET u.password = :password WHERE u.email = :email AND u.phoneNumber = :phoneNumber")
    void updatePasswordByEmailAndPhoneNumber(
            @Param("email") String email,
            @Param("phoneNumber") String phoneNumber,
            @Param("password") String password
    );

    @Modifying
    @Transactional
    @Query("UPDATE Auth u SET u.password = :password WHERE u.memberUuid = :memberUuid")
    void updatePasswordByMemberUuid(
            @Param("memberUuid") String memberUuid,
            @Param("password") String password
    );


}
