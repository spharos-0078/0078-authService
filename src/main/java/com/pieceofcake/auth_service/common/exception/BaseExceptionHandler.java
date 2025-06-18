package com.pieceofcake.auth_service.common.exception;

import com.pieceofcake.auth_service.common.entity.BaseResponseEntity;
import com.pieceofcake.auth_service.common.entity.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    /**
     * 발생한 예외 처리
     */

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<BaseResponseEntity<Void>> BaseError(BaseException e) {
        BaseResponseEntity<Void> response = new BaseResponseEntity<>(e.getStatus());
        log.error("BaseException -> {}({})", e.getStatus(), e.getStatus().getMessage(), e);
        return new ResponseEntity<>(response, response.httpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<BaseResponseEntity<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String errorMessage = "요청 본문을 읽을 수 없습니다. 입력 형식을 확인해주세요.";

        log.warn("HTTP 메시지 변환 오류: {}", e.getMessage());

        BaseResponseEntity<Void> response = new BaseResponseEntity<>(
                BaseResponseStatus.INVALID_REQUEST.getHttpStatusCode(),
                false,
                errorMessage,
                BaseResponseStatus.INVALID_REQUEST.getCode(),
                null
        );
        return new ResponseEntity<>(response, response.httpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseResponseEntity<Void>> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        FieldError fieldError = bindingResult.getFieldError();
        String errorMessage = (fieldError != null)
                ? String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage())
                : "잘못된 요청입니다.";

        log.warn("Validation failed: {}", errorMessage);

        BaseResponseEntity<Void> response = new BaseResponseEntity<>(
                BaseResponseStatus.INVALID_REQUEST.getHttpStatusCode(),
                false,
                errorMessage,
                BaseResponseStatus.INVALID_REQUEST.getCode(),
                null
        );
        return new ResponseEntity<>(response, response.httpStatus());
    }


//    /**
//     * security 인증 에러
//     * 아이디가 없거나 비밀번호가 틀린 경우 AuthenticationManager 에서 발생
//     *
//     * @return FAILED_TO_LOGIN 에러 response
//     */
//    @ExceptionHandler(BadCredentialsException.class)
//    protected ResponseEntity<BaseResponseEntity<Void>> handleBadCredentialsException(BaseException e) {
//        BaseResponseEntity<Void> response = new BaseResponseEntity<>(BaseResponseStatus.FAILED_TO_LOGIN);
//        log.error("BaseException: ", e);
//        return new ResponseEntity<>(response, response.httpStatus());
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    protected ResponseEntity<BaseResponseEntity<Void>> RuntimeError(RuntimeException e) {
//        BaseResponseEntity<Void> response = new BaseResponseEntity<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
//        log.error("RuntimeException: ", e);
//        for (StackTraceElement s : e.getStackTrace()) {
//            System.out.println(s);
//        }
//        return new ResponseEntity<>(response, response.httpStatus());
//    }

}
