package com.example.r2g.domain.error.repository;

import com.example.r2g.domain.error.dto.ErrorSearchCondition;
import com.example.r2g.domain.error.entity.ErrorLog;

import java.util.List;

public interface ErrorRepositoryCustom {

    List<ErrorLog> search(ErrorSearchCondition condition);
}
