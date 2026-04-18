package com.example.r2g.domain.error.controller;

import com.example.r2g.domain.error.dto.ErrorDetailResponse;
import com.example.r2g.domain.error.dto.ErrorRequest;
import com.example.r2g.domain.error.dto.ErrorResponse;
import com.example.r2g.domain.error.service.ErrorService;
import com.example.r2g.domain.user.entity.User;
import com.example.r2g.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/errors")
@RequiredArgsConstructor
public class ErrorController {

    private final ErrorService errorService;

    /**
     * 에러 로그 생성 API
     * POST /errors
     */
    @PostMapping
    public ApiResponse<ErrorResponse> createError(@RequestBody @Valid ErrorRequest request) {
       //TODO : Security 연동
        User user = null;
        ErrorResponse response = errorService.createError(request, user);

        return ApiResponse.success(response);
    }

    /**
     * 에러 로그 목록 조회 API
     * GET /errors
     */
    @GetMapping
    public ApiResponse<List<ErrorResponse>> getErrors(){
        List<ErrorResponse> list = errorService.getErrors();

        return ApiResponse.success(list);
    }

    /**
     * 에러 로그 상세 조회 API
     * GET /errors/{errorId}
     */
    @GetMapping("/{errorId}")
    public ApiResponse<ErrorDetailResponse> getErrorDetail(@PathVariable Long errorId){
        ErrorDetailResponse response = errorService.getErrorDetail(errorId);
        return ApiResponse.success(response);
    }


}
