package com.shop.repository;

import com.shop.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryDSLRepository2 {
    List<Board> findByTitle(String title);
}
