package com.example.r2g.domain.error.service;

import com.example.r2g.domain.error.dto.ErrorDetailResponse;
import com.example.r2g.domain.error.dto.ErrorRequest;
import com.example.r2g.domain.error.dto.ErrorResponse;
import com.example.r2g.domain.error.dto.ErrorSearchCondition;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
public class ErrorService {

    private final ErrorRepository errorRepository;
    private final UserRepository userRepository;

    private static final String GUEST_EMAIL = "guest@red2green.local";

    /**
     * 에러 등록하기
     * */
    public ErrorResponse createError(ErrorRequest request, User user){

        User owner = (user != null) ? user : userRepository.findByEmail(GUEST_EMAIL)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));


        String errorType = extractErrorType(request.getRawMessage());

        ErrorLog error = ErrorLog.builder()
                .user(owner)
                .rawMessage(request.getRawMessage())
                // TODO : 나중에 로그 정제 로직
                .normalizedMessage(request.getRawMessage())
                .language(request.getLanguage())
                .framework(request.getFramework())
                .errorType(errorType)
                .build();

        errorRepository.save(error);
        return ErrorResponse.from(error);

    }


    /**
     * 에러 로그 목록 조회하기
     * */
    @Transactional(readOnly = true)
    public List<ErrorResponse> getErrors(){
        return errorRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(ErrorResponse::from)
                .toList();
    }

    /**
     * 에러 로그 상세 조회하기
     * */
    @Transactional(readOnly = true)
    public ErrorDetailResponse getErrorDetail(Long id){

        ErrorLog error = findErrorById(id);

        return ErrorDetailResponse.from(error);

    }

    /**
     * 에러 로그 삭제하기
     * */
    public void deleteError(Long id){
        // TODO: Security 연동 후 로그인 사용자와 에러 로그 작성자가 일치하는지 검증한 뒤 삭제 허용
        throw new CustomException(ErrorCode.UNAUTHORIZED);
    }

    /**
     * 에러 로그 검색하기
     * */
    public List<ErrorResponse> searchErrors(ErrorSearchCondition condition){
        return errorRepository.search(condition)
                .stream()
                .map(ErrorResponse::from)
                .toList();
    }

    private ErrorLog findErrorById(Long id) {
        return errorRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.ERROR_NOT_FOUND));
    }

    // TODO : 추후 AI 또는 정교한 파싱 로직을 적용해 에러 타입 추출 정확도 개선
    private String extractErrorType(String rawMessage) {
        if (rawMessage == null || rawMessage.isBlank()) {
            return "UNKNOWN";
        }

        Pattern pattern = Pattern.compile("([a-zA-Z0-9_$.]+Exception|[a-zA-Z0-9_$.]+Error)");
        Matcher matcher = pattern.matcher(rawMessage);

        if (matcher.find()) {
            String fullName = matcher.group(1);

            int lastDotIndex = fullName.lastIndexOf(".");
            if (lastDotIndex != -1) {
                return fullName.substring(lastDotIndex + 1);
            }

            return fullName;
        }

        return "UNKNOWN";
    }


}
