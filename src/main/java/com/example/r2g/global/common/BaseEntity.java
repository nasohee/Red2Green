package com.example.r2g.global.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 다른 엔티티들이 이 클래스 상속받으면 아래 필드들 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class) // 생성일/수정일 자동으로 기록 (Auditing 기능)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false) // 한번 생성되면 업데이트 되지 않도록
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}