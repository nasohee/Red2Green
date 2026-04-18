package com.example.r2g.domain.error.dto;

import com.example.r2g.domain.error.entity.ErrorLog;
import com.example.r2g.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private Long errorId;
    private String rawMessage;
    private String language;
    private String framework;
    private LocalDateTime createdAt;

    // TODO : AI 붙이면 수정
    public static ErrorResponse from(ErrorLog error) {
        return new ErrorResponse(
                error.getId(),
                error.getRawMessage(),
                error.getLanguage(),
                error.getFramework(),
                error.getCreateAt()
        );
    }
}
