package com.example.r2g.domain.error.dto;

import com.example.r2g.domain.error.entity.ErrorLog;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorDetailResponse {
    private Long errorId;
    private String rawMessage;
    private String normalizedMessage;
    private String language;
    private String framework;
    private String errorType;
    private LocalDateTime createdAt;

    // TODO : cause,, solution 추후 추가

    public static ErrorDetailResponse from(ErrorLog error) {
        return new ErrorDetailResponse(
                error.getId(),
                error.getRawMessage(),
                error.getNormalizedMessage(),
                error.getLanguage(),
                error.getFramework(),
                error.getErrorType(),
                error.getCreatedAt()
        );
    }

}
