package com.example.r2g.domain.post.entity;

import com.example.r2g.domain.error.entity.ErrorLog;
import com.example.r2g.domain.user.entity.User;
import com.example.r2g.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "troubleshooting_posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TroubleShootingPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 한 user가 여러 post를 작성할 수 있는 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 특정 에러와 연결된 trouble shooting 기록 (선택적 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "error_id")
    private ErrorLog error;

    // 게시글 제목
    @Column(nullable = false, length = 255)
    private String title;

    // 트러블 슈팅 상세 내용
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 태그 정보
    @Column(length = 255)
    private String tags;

    // 생성 메서드
    public static TroubleShootingPost create(User user, ErrorLog error, String title, String content, String tags) {
        return TroubleShootingPost.builder()
                .user(user)
                .error(error)
                .title(title)
                .content(content)
                .tags(tags)
                .build();
    }
}