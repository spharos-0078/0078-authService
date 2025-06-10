package com.pieceofcake.auth_service.common.util;

import com.pieceofcake.auth_service.common.jwt.JwtProvider;
import com.pieceofcake.auth_service.member.dto.out.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final RedisUtil redisUtil;
    private final JwtProvider jwtProvider;

    public LoginResponseDto createLoginToken(String memberUuid) {
        try {
            final Authentication authentication = new UsernamePasswordAuthenticationToken(
                    memberUuid,
                    null,
                    List.of()
            );

            final String accessToken = jwtProvider.generateAccessToken(authentication);
            final String refreshToken = jwtProvider.generateRefreshToken(authentication);

            redisUtil.set(
                    "Access:" + authentication.getName(),
                    accessToken,
                    1,
                    TimeUnit.DAYS
            );

            redisUtil.set(
                    "Refresh:" + authentication.getName(),
                    refreshToken,
                    14,
                    TimeUnit.DAYS
            );
            return LoginResponseDto.of(accessToken, refreshToken);
        } catch (Exception e) {
            throw new IllegalArgumentException("로그인 토큰 생성에 실패했습니다.", e);
        }
    }

}

