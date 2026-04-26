package com.example.r2g.domain.error.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ErrorRequest {
    // 원본 로그
    @NotBlank(message = "에러 로그는 필수입니다.")
    private String rawMessage;

    // 언어
    @NotBlank(message = "언어는 필수입니다.")
    @Size(max = 50, message = "언어는 50자 이하여야 합니다.")
    private String language;

    // 프레임워크
    @Size(max = 50, message = "프레임워크는 50자 이하여야 합니다.")
    private String framework;


}
