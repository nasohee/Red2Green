package com.example.r2g.domain.error.repository;

import com.example.r2g.domain.error.dto.ErrorSearchCondition;
import com.example.r2g.domain.error.entity.ErrorLog;

import com.example.r2g.domain.error.entity.QErrorLog;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class ErrorRepositoryImpl implements ErrorRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    QErrorLog errorLog = QErrorLog.errorLog;


    @Override
    public List<ErrorLog> search(ErrorSearchCondition condition) {

        return queryFactory
                .selectFrom(errorLog)
                .where(
                        containsKeyword(condition.getKeyword()),
                        eqLanguage(condition.getLanguage()),
                        eqFramework(condition.getFramework())
                )
                .orderBy(errorLog.createdAt.desc())
                .fetch();
    }

    private BooleanExpression containsKeyword(String keyword){
        return StringUtils.hasText(keyword) ? errorLog.rawMessage.containsIgnoreCase(keyword) : null;
    }

    private BooleanExpression eqLanguage(String language){
        return StringUtils.hasText(language) ? errorLog.language.containsIgnoreCase(language) : null;
    }

    private BooleanExpression eqFramework(String framework){
        return StringUtils.hasText(framework) ? errorLog.framework.containsIgnoreCase(framework) : null;
    }

}
