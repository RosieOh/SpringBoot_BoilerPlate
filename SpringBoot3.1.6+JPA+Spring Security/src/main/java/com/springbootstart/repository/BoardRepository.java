package com.springbootstart.repository;

import com.springbootstart.domain.Board;
import com.springbootstart.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
