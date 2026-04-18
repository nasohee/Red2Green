package com.example.r2g.domain.error.entity;

import com.example.r2g.domain.user.entity.User;
import com.example.r2g.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "error_logs")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 한 User가 여러 에러를 생성할 수 있는 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 원본 에러 로그
    @Column(name = "raw_message", columnDefinition = "TEXT", nullable = false)
    private String rawMessage;

    // 정제된 에러 (검색용)
    @Column(name = "normalized_message", columnDefinition = "TEXT", nullable = false)
    private String normalizedMessage;

    // 에러 필터링 및 분류를 위한 언어 정보
    @Column(length = 50)
    private String language;

    // 에러의 종류
    private String errorType;

    // 프레임워크 단위 분류 (Spring 등)
    @Column(length = 50)
    private String framework;

    // 유사도 검색을 위한 임베딩 벡터
    // 초기에 null 허용, 이후 embedding 생성 후 업데이트
    @Column(columnDefinition = "TEXT")
    private String embedding;


    // 생성 메서드
    public static ErrorLog create(User user, String rawMessage, String normalizedMessage) {
        return ErrorLog.builder()
                .user(user)
                .rawMessage(rawMessage)
                .normalizedMessage(normalizedMessage)
                .build();
    }
}


