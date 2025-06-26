package com.pieceofcake.auth_service.common.util;

import com.pieceofcake.auth_service.common.entity.BaseResponseStatus;
import com.pieceofcake.auth_service.common.exception.BaseException;
import com.pieceofcake.auth_service.common.jwt.JwtProvider;
import com.pieceofcake.auth_service.auth.dto.out.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final RedisUtil redisUtil;
    private final JwtProvider jwtProvider;

    public LoginResponseDto createLoginToken(String memberUuid, String role) {
        try {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

            final Authentication authentication = new UsernamePasswordAuthenticationToken(
                    memberUuid,
                    null,
                    List.of(authority)
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
            return LoginResponseDto.of(accessToken, refreshToken, memberUuid);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.JWT_TOKEN_GENERATION_FAILED);
        }
    }

}

