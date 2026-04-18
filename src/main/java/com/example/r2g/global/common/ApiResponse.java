package com.example.r2g.global.common;

import com.example.r2g.global.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "timestamp",
        "success",
        "status",
        "code",
        "message",
        "data"
})
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final boolean success; // 성공 여부
    private final int status;      // HTTP 상태 코드 (예: 200, 400)
    private final String code;        // 비즈니스 에러 코드 (예: 200, 4001)
    private final String message;   // 응답 메시지
    private final T data;           // 실제 결과 데이터 (성공 시에만 존재)

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, HttpStatus.OK.value(), String.valueOf(HttpStatus.OK.value()), "요청에 성공하였습니다.", data);
    }

    public static ApiResponse<Void> error(ErrorCode errorCode) {
        return new ApiResponse<>(
                false,
                errorCode.getHttpStatus().value(),
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );
    }

    public static ApiResponse<Void> error(int status, String code, String message) {
        return new ApiResponse<>(false, status, code, message, null);
    }
}
