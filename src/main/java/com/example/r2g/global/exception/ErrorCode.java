package com.example.r2g.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 공통
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "4000", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5000", "서버 오류가 발생했습니다."),

    // Validation
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "4001", "입력값 검증에 실패했습니다."),

    // Error 도메인
    ERROR_NOT_FOUND(HttpStatus.NOT_FOUND, "4040", "해당 에러를 찾을 수 없습니다."),

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "4041", "사용자를 찾을 수 없습니다."),

    // Auth
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "4010", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "4030", "접근 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}