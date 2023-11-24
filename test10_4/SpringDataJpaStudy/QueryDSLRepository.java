package com.shop.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.Board;
import com.shop.domain.QBoard;
import com.shop.repository.search.BoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class QueryDSLRepository {

    @Autowired
    private JPAQueryFactory queryFactory;

    public List<Board> findByTitle(String title){
        QBoard board = new QBoard(title);
        return queryFactory.selectFrom(board).where(board.title.eq(title)).fetch();
    }

}
