package com.lms.domain.board.repository;

import com.lms.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b where b.id = :id")
    Optional<Board> findById(@Param("id") Long id);

    @Query("select b from Board b where b.boardType = :boardType")
    List<Board> findByBoardType(@Param("boardType") String boardType);

    @Query("select b from Board b where b.writer = :writer")
    Optional<Board> findByName(@Param("writer") String writer);

    Page<Board> findByBoardType(String boardType, Pageable pageable);

}
