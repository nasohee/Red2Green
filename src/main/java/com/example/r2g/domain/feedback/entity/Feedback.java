package com.example.r2g.domain.feedback.entity;

import com.example.r2g.domain.solution.entity.Solution;
import com.example.r2g.domain.user.entity.User;
import com.example.r2g.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "feedback",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "solution_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Feedback extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 여러 개의 feedback이 solution 하나에 속하는 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solution_id", nullable = false)
    private Solution solution;

    // 한 user가 여러 solution에 대해 피드백을 남길 수 있는 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 해결책에 대한 성공 여부 (1: 성공, 0: 실패)
    @Column(nullable = false)
    private boolean isSuccess;

    // 생성 메서드
    public static Feedback create(User user, Solution solution, boolean isSuccess) {
        return Feedback.builder()
                .user(user)
                .solution(solution)
                .isSuccess(isSuccess)
                .build();
    }
}