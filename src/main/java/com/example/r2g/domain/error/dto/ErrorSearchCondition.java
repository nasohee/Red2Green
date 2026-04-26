package com.example.r2g.domain.error.dto;

import lombok.Data;

@Data
public class ErrorSearchCondition {
    private String keyword;
    private String language;
    private String framework;
}
