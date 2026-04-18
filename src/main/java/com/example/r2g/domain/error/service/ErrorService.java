package com.example.r2g.domain.error.service;

import com.example.r2g.domain.error.dto.ErrorRequest;
import com.example.r2g.domain.error.dto.ErrorResponse;
import com.example.r2g.domain.error.entity.ErrorLog;
import com.example.r2g.domain.error.repository.ErrorRepository;
import com.example.r2g.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ErrorService {

    private final ErrorRepository errorRepository;

    /**
     * 에러 등록하기
     * */
    public ErrorResponse createError(ErrorRequest request, User user){

        ErrorLog error = ErrorLog.builder()
                .user(user)
                .rawMessage(request.getRawMessage())
                // TODO : 나중에 록 정제 로직
                .normalizedMessage(request.getRawMessage())
                .language(request.getLanguage())
                .framework(request.getFramework())
                .build();

        errorRepository.save(error);
        return ErrorResponse.from(error);

    }
}
