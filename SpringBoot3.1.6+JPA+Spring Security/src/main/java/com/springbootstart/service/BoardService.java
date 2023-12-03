package com.springbootstart.service;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.page.PageRequestDTO;
import com.springbootstart.dto.page.PageResponseDTO;

// 465P 부터 시작
public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
