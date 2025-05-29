package com.pieceofcake.auth_service.member.infrastructure;

import com.pieceofcake.auth_service.member.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     *
     */
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNickname(String nickname);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByPhoneNumberAndName(String phoneNumber, String name);

    @Modifying
    @Transactional  // 이 어노테이션 꼭 필요함!
    @Query("UPDATE Member u SET u.password = :password WHERE u.email = :email AND u.phoneNumber = :phoneNumber")
    int updatePassword(
            @Param("email") String email,
            @Param("phoneNumber") String phoneNumber,
            @Param("password") String password
    );
}
