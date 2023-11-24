package com.shop.repository;

import com.shop.domain.Board;
import com.shop.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//JpaRepository는 제네릭타입의 인터페이스로 findAll(), save(), saveAll(), delete() 등 을 기본으로 제공
//JPQL(Java Persistence Query Language) :
public interface JPQLRepository extends JpaRepository<Board, Long>, BoardSearch {

    @Query("select b from Board b where b.bno =:bno")
    Optional<Board> findBoardById(Long bno);

    @Query("select b from Board b")
    List<Board> findBoardAll();

    @Query("update Board set title =:title, content =:content where Board.bno =:bno")
    void updateBoard(@Param("title") String title, @Param("content") String content, @Param("bno") Long bno);

}
