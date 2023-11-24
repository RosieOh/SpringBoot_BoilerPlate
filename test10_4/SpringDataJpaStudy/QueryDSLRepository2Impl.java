package com.shop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.Board;
import com.shop.domain.QBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class QueryDSLRepository2Impl implements QueryDSLRepository2 {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Board> findByTitle(String title) {
        QBoard board = new QBoard(title);
        return queryFactory.selectFrom(board).where(board.title.eq(title)).fetch();
    }
}
