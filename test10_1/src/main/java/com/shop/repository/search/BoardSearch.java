package com.shop.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.shop.domain.Board;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
