package com.example.r2g.domain.error.service;

import com.example.r2g.domain.error.dto.ErrorRequest;
import com.example.r2g.domain.error.dto.ErrorResponse;
import com.example.r2g.domain.error.entity.ErrorLog;
import com.example.r2g.domain.error.repository.ErrorRepository;
import com.example.r2g.domain.user.entity.User;
import com.example.r2g.domain.user.repository.UserRepository;
import com.example.r2g.global.exception.CustomException;
import com.example.r2g.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ErrorService {

    private final ErrorRepository errorRepository;
    private final UserRepository userRepository;

    private static final String GUEST = "GUEST";

    /**
     * 에러 등록하기
     * */
    public ErrorResponse createError(ErrorRequest request, User user){

        User owner = (user != null) ? user : userRepository.findByNickname(GUEST)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        ErrorLog error = ErrorLog.builder()
                .user(owner)
                .rawMessage(request.getRawMessage())
                // TODO : 나중에 록 정제 로직
                .normalizedMessage(request.getRawMessage())
                .language(request.getLanguage())
                .framework(request.getFramework())
                .build();

        errorRepository.save(error);
        return ErrorResponse.from(error);

    }

    /**
     * 에러 단건 조회하기
     * */
    @Transactional(readOnly = true)
    public List<ErrorResponse> getErrors(){
        return errorRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(ErrorResponse::from)
                .toList();
    }
}
