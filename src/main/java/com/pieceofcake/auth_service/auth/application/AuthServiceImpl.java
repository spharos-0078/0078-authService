package com.pieceofcake.auth_service.auth.application;

import com.pieceofcake.auth_service.auth.entity.enums.MemberStatus;
import com.pieceofcake.auth_service.common.entity.BaseResponseStatus;
import com.pieceofcake.auth_service.common.exception.BaseException;
import com.pieceofcake.auth_service.common.jwt.JwtProvider;
import com.pieceofcake.auth_service.common.util.JwtUtil;
import com.pieceofcake.auth_service.common.util.PasswordGeneratorUtil;
import com.pieceofcake.auth_service.common.util.RedisUtil;
import com.pieceofcake.auth_service.auth.dto.in.*;
import com.pieceofcake.auth_service.auth.dto.out.*;
import com.pieceofcake.auth_service.auth.entity.Auth;
import com.pieceofcake.auth_service.auth.infrastructure.AuthRepository;
import com.pieceofcake.auth_service.kafka.producer.SignUpKafkaProducer;
import com.pieceofcake.auth_service.kafka.producer.event.SignUpEvent;
import com.pieceofcake.auth_service.smsverify.application.PhoneService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtil redisUtil;
    private final JwtProvider jwtProvider;
    private final JwtUtil jwtUtil;
    private final PhoneService phoneService;
    private final SignUpKafkaProducer signupKafkaProducer;


    @Transactional
    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) {

        if (authRepository.existsByEmail(signUpRequestDto.getEmail())) {
            Auth auth = authRepository.findByEmail(signUpRequestDto.getEmail())
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EMAIL));

            throw new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
        }
        if (authRepository.existsByPhoneNumber(signUpRequestDto.getPhoneNumber())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_PHONE_NUMBER);
        }
//        if (authRepository.existsByNickname(signUpRequestDto.getNickname())) {
//            throw new BaseException(BaseResponseStatus.DUPLICATED_NICKNAME);
//        }
//        if (!"true".equals(redisUtil.get("sms:SIGN_UP:Verified:" + signUpRequestDto.getPhoneNumber()))) {
//            throw new BaseException(BaseResponseStatus.SMS_VERIFICATION_NOT_COMPLETED);
//        }
        Auth auth = signUpRequestDto.toEntity(passwordEncoder);
        authRepository.save(auth);

        // ✅ Kafka로 회원가입 정보 전송 (이메일, 이름, 전화번호 등)
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                SignUpEvent event = SignUpEvent.builder()
                        .memberUuid(auth.getMemberUuid())
                        .email(signUpRequestDto.getEmail())
                        .name(signUpRequestDto.getName())
                        .phoneNumber(signUpRequestDto.getPhoneNumber())
                        .birthdate(signUpRequestDto.getBirthdate())
                        .gender(signUpRequestDto.getGender())
                        .nickname(signUpRequestDto.getNickname())
                        .build();
            // ✅ Kafka 전송
            signupKafkaProducer.sendSignUpEvent(event);
            }
        });
    }

    @Transactional
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        final Auth auth = authRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EMAIL));

        // 비밀번호 검증. 차후 배포 단계에서 에러 메시지 자세한 내역은 숨길 것.
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), auth.getPassword())) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
        } else if (auth.getStatus() == MemberStatus.DISABLED) {
            throw new BaseException(BaseResponseStatus.DISABLED_MEMBER);
        } else if (auth.getStatus() == MemberStatus.BLACKED) {
            throw new BaseException(BaseResponseStatus.BLACKED_MEMBER);
        } else if (auth.getStatus() == MemberStatus.DELETED) {
            throw new BaseException(BaseResponseStatus.DELETED_MEMBER);
        }

        return jwtUtil.createLoginToken(auth.getMemberUuid(), auth.getRole().toString());

    }

    @Transactional
    @Override
    public void logout(String memberUuid) {
        redisUtil.delete("Access:" + memberUuid);
    }

    @Transactional
    @Override
    public void resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) {
        if (!"true".equals(redisUtil.get("sms:RESET_PASSWORD:Verified:" + resetPasswordRequestDto.getPhoneNumber()))) {
            throw new BaseException(BaseResponseStatus.SMS_VERIFICATION_NOT_COMPLETED);
        }
        if (!authRepository.existsByEmail(resetPasswordRequestDto.getEmail())) {
            throw new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND);
        }
        if (!authRepository.existsByPhoneNumber(resetPasswordRequestDto.getPhoneNumber())) {
            throw new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND);
        }

        // 비밀번호 생성
        String tempPassword = PasswordGeneratorUtil.generate();
        String encodedPassword = passwordEncoder.encode(tempPassword);
        // 비밀번호 저장
        authRepository.updatePasswordByEmailAndPhoneNumber(
                resetPasswordRequestDto.getEmail(),
                resetPasswordRequestDto.getPhoneNumber(),
                encodedPassword
        );
        //비밀번호 SMS 전송
        phoneService.sendNewPassword(resetPasswordRequestDto.getPhoneNumber(), tempPassword);
    }

    @Transactional
    @Override
    public void changePassword(ChangePasswordRequestDto changePasswordRequestDto) {

        Auth auth = authRepository.findByMemberUuid(changePasswordRequestDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(changePasswordRequestDto.getOldPassword(), auth.getPassword())) {
            throw new BaseException(BaseResponseStatus.INVALID_CURRENT_PASSWORD);
        }

        String newEncodedPassword = passwordEncoder.encode(changePasswordRequestDto.getNewPassword());
        authRepository.updatePasswordByMemberUuid(auth.getMemberUuid(), newEncodedPassword);
    }

}
