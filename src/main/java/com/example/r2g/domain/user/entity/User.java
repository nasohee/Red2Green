package com.example.r2g.domain.user.entity;

import com.example.r2g.domain.error.entity.ErrorLog;
import com.example.r2g.domain.feedback.entity.Feedback;
import com.example.r2g.domain.post.entity.TroubleShootingPost;
import com.example.r2g.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickname;

    // 사용자 기술 스택 정보 (추천 및 필터링 용도)
    @Column(length = 255)
    private String techStack;


    // 사용자가 등록한 에러 목록 (1:N)
    @OneToMany(mappedBy = "user")
    private List<ErrorLog> errors = new ArrayList<>();

    // 사용자가 작성한 트러블슈팅 게시글 목록 (1:N)
    @OneToMany(mappedBy = "user")
    private List<TroubleShootingPost> posts = new ArrayList<>();

    // 사용자가 남긴 피드백 목록 (1:N)
    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks = new ArrayList<>();

    // 생성 메서드
    public static User create(String email, String password, String nickname) {
        return User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}