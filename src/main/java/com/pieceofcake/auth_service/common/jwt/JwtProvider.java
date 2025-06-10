package com.pieceofcake.auth_service.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.access-expire-time}")
    private long accessTokenExpiry;

    @Value("${jwt.token.refresh-expire-time}")
    private long refreshTokenExpiry;

    private Key key;

    @PostConstruct
    public void init() {
        // jwt secret key를 byte 배열로 변환하여 Key 객체 생성
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 2. Claims에서 원하는 claim 값 추출
     * @param token
     * @param claimsResolver jwt토큰에서 추출한 정보를 어떻게 처리할지 결정하는 함수
     * @return jwt토큰에서 모든 클레임(클레임은 토큰에 담긴 정보 의미 ) 추출한 다음 claimsResolver함수를 처리해 결과 반환
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 3. 토큰에서 모든 claims 추출
     * @param token
     * @return jwt토큰에서 모든 클레임 추출
     */
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Access Token 생성
     */
    public String generateAccessToken(Authentication authentication) {
        String memberUuid = (String) authentication.getPrincipal();     // 현재 로그인한 사용자의 정보
        Date now = new Date();
        Date expiration = new Date(now.getTime() + accessTokenExpiry * 1000);

        return Jwts.builder()
                .claim("token_type", "access")
                .claim("memberUuid", memberUuid)
                .subject(authentication.getName())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    /**
     * Refresh Token 생성
     */
    public String generateRefreshToken(Authentication authentication) {
        String memberUuid = (String) authentication.getPrincipal();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + refreshTokenExpiry * 1000);

        return Jwts.builder()
                .claim("token_type", "refresh")
                .claim("memberUuid", memberUuid)
                .subject(authentication.getName())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    /**
     * 토큰 유효성 검증 (서명 확인 및 만료 시간 체크)
     * @param token
     * @return 유효하면 true, 아니면 false
     */
    public boolean validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


}
