package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.domain.Board;
import com.shop.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {


}
