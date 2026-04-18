package com.example.r2g.domain.error.repository;

import com.example.r2g.domain.error.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<ErrorLog, Long> {
}
