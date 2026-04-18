package com.example.r2g.domain.error.controller;

import com.example.r2g.domain.error.dto.ErrorRequest;
import com.example.r2g.domain.error.dto.ErrorResponse;
import com.example.r2g.domain.error.service.ErrorService;
import com.example.r2g.domain.user.entity.User;
import com.example.r2g.domain.user.repository.UserRepository;
import com.example.r2g.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/errors")
@RequiredArgsConstructor
public class ErrorController {

    private final ErrorService errorService;
    private final UserRepository userRepository;

    /**
     * 에러 로그 생성 API
     * POST /errors
     */
    @PostMapping
    public ApiResponse<ErrorResponse> createError(@RequestBody @Valid ErrorRequest request) {
        // 임시 유저
        User user = userRepository.save(
                User.builder()
                        .email("test@test.com").nickname("test").password("1234").build()
        );
        ErrorResponse response = errorService.createError(request, user);

        return ApiResponse.success(response);
    }

}
