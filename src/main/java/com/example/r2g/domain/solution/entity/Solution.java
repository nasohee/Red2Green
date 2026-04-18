package com.example.r2g.domain.solution.entity;

import com.example.r2g.domain.error.entity.ErrorLog;
import com.example.r2g.domain.feedback.entity.Feedback;
import com.example.r2g.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solutions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Solution extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 하나의 에러에 대해 여러 solution 존재할 수 있는 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "error_id", nullable = false)
    private ErrorLog error;

    // 핵심 해결 내용을 요약한 3줄 요약
    @Column(columnDefinition = "TEXT", nullable = false)
    private String summary;

    // 상세 해결 과정 및 설명
    @Column(columnDefinition = "TEXT")
    private String detail;

    // solution 생성 주체 (AI / USER)
    @Column(nullable = false, length = 20)
    private String source;

    // 해결 성공 횟수
    @Column(nullable = false)
    private int successCount;

    // 해결 실패 횟수
    @Column(nullable = false)
    private int failCount;

    // 하나의 solution에 대해 여러 feedback이 존재할 수 있는 1:N 관계
    @OneToMany(mappedBy = "solution")
    private List<Feedback> feedbacks = new ArrayList<>();

    // 생성 메서드
    public static Solution create(ErrorLog error, String summary, String detail, String source) {
        return Solution.builder()
                .error(error)
                .summary(summary)
                .detail(detail)
                .source(source)
                .successCount(0)
                .failCount(0)
                .build();
    }

    public void increaseSuccess() {
        this.successCount++;
    }

    public void increaseFail() {
        this.failCount++;
    }

}
