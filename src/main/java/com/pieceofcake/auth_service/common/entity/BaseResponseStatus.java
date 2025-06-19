package com.pieceofcake.auth_service.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),
    SMS_CODE_SUCCESS(HttpStatus.OK, true, 201, "휴대폰 인증코드 발송에 성공하였습니다."),
    SMS_CODE_VERIFICATION_SUCCESS(HttpStatus.OK, true, 202, "휴대폰 인증에 성공하였습니다."),
    SIGN_UP_SUCCESS(HttpStatus.OK, true, 203, "회원가입에 성공하였습니다."),
    SIGN_IN_SUCCESS(HttpStatus.OK, true, 204, "로그인에 성공하였습니다."),
    LOGOUT_SUCCESS(HttpStatus.OK, true, 205, "로그아웃 되었습니다."),
    PASSWORD_RESET_SUCCESS(HttpStatus.OK, true, 207, "비밀번호가 초기화되었습니다."),
    PASSWORD_CHANGE_SUCCESS(HttpStatus.OK, true, 207, "비밀번호가 변경되었습니다."),
    CHECK_EMAIL_SUCCESS(HttpStatus.OK, true, 208, "이메일 중복 확인에 성공하였습니다."),
    MEMBER_UPDATE_SUCCESS(HttpStatus.OK, true, 209, "회원 정보 수정에 성공하였습니다."),

    /**
     * 400 : security 에러
     */
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, false, 400, "토큰이 존재하지 않습니다"),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, false, 401, "유효하지 않은 접근 토큰입니다"),
    WRONG_JWT_TOKEN(HttpStatus.UNAUTHORIZED, false, 401, "다시 로그인 해주세요"),
    NO_SIGN_IN(HttpStatus.UNAUTHORIZED, false, 402, "로그인을 먼저 진행해주세요"),
    NO_ACCESS_AUTHORITY(HttpStatus.FORBIDDEN, false, 403, "접근 권한이 없습니다"),
    DISABLED_USER(HttpStatus.FORBIDDEN, false, 404, "비활성화된 계정입니다. 계정을 복구하시겠습니까?"),
    FAILED_TO_RESTORE(HttpStatus.INTERNAL_SERVER_ERROR, false, 405, "계정 복구에 실패했습니다. 관리자에게 문의해주세요."),
    NO_EXIST_OAUTH(HttpStatus.NOT_FOUND, false, 406, "소셜 로그인 정보가 존재하지 않습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, false, 407, "잘못된 요청입니다. 요청 형식을 확인해주세요."),
    /**
     * 2000: users service error
     */
    // token
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, false, 2001, "토큰이 유효하지 않습니다."),
    JWT_TOKEN_GENERATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, false, 2002, "JWT 토큰 생성에 실패했습니다."),

    // Users
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, false, 2101, "이미 가입된 이메일입니다."),
    DUPLICATED_PHONE_NUMBER(HttpStatus.CONFLICT, false, 2101, "이미 가입된 휴대폰 번호입니다."),
    DUPLICATED_NICKNAME(HttpStatus.CONFLICT, false, 2102, "이미 가입된 닉네임입니다."),
    SMS_VERIFICATION_NOT_COMPLETED(HttpStatus.UNAUTHORIZED, false, 2103, "휴대폰 인증이 완료되지 않았습니다."),
    FAILED_TO_LOGIN(HttpStatus.UNAUTHORIZED, false, 2104, "아이디 또는 패스워드를 다시 확인하세요."),
    SMS_CERTIFICATION_NOT_FOUND(HttpStatus.UNAUTHORIZED, false, 2105, "인증 코드가 존재하지 않습니다."),
    SMS_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED, false, 2106, "인증 코드가 일치하지 않습니다."),
    DISABLED_MEMBER(HttpStatus.FORBIDDEN, false, 2108, "비활성화된 계정입니다. 관리자에게 문의해주세요."),
    BLACKED_MEMBER(HttpStatus.FORBIDDEN, false, 2109, "블랙리스트에 등록된 계정입니다. 관리자에게 문의해주세요."),
    DELETED_MEMBER(HttpStatus.FORBIDDEN, false, 2110, "삭제된 계정입니다. 관리자에게 문의해주세요."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, false, 2111, "존재하지 않는 회원 내역입니다."),
    INVALID_CURRENT_PASSWORD(HttpStatus.BAD_REQUEST, false, 2112, "현재 비밀번호가 일치하지 않습니다."),
    NO_EXIST_EMAIL(HttpStatus.NOT_FOUND, false, 2113, "존재하지 않는 이메일입니다."),
    SMS_SEND_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 2114, "SMS 전송 중 오류가 발생했습니다."),
    SMS_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, false, 2115, "SMS 전송에 실패했습니다."),



    INVALID_USER_INPUT(HttpStatus.BAD_REQUEST, false, 3000, "유효하지 않은 사용자 입력입니다.");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;

}


