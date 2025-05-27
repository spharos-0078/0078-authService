package com.pieceofcake.auth_service.member.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@RequiredArgsConstructor
@Repository
public class SendPhoneCodeDao {
    private final String PREFIX = "sms:";
    private final int LIMIT_TIME = 60 * 60;      // redis에 저장하는 시간 3분으로 설정

    private final StringRedisTemplate redisTemplate;

    public void createSmsCertification(String phone, String certificationNumber, String purpose) {
        redisTemplate.opsForValue()
                .set(PREFIX + purpose + ":" + phone, certificationNumber, Duration.ofSeconds(LIMIT_TIME));
    }

    public void createSmsVerifed(String phone, String purpose) {
        redisTemplate.opsForValue()
                .set(PREFIX + purpose + ":Verified:" + phone,
                        "true", Duration.ofSeconds(LIMIT_TIME));
    }

    public String getSmsCertification(String phone, String purpose) {
        return redisTemplate.opsForValue().get(PREFIX + purpose + ":" + phone);
    }

    public void removeSmsCertification(String phone, String purpose) {
        redisTemplate.delete(PREFIX + purpose + ":" + phone);
    }

    public boolean hasKey(String phone, String purpose) {
        return redisTemplate.hasKey(PREFIX + purpose + ":" + phone);
    }
}
