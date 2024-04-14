package com.lms.domain.board.service;

import com.lms.domain.board.dto.BoardDTO;
import com.lms.domain.board.entity.Board;

import java.util.List;

public interface BoardService {

    public BoardDTO findById(Long id);


    public List<BoardDTO> findAll(BoardDTO boardDTO);

    // test
    public List<Board> boardList();

    public void register(BoardDTO boardDTO);

    public void modify(BoardDTO boardDTO);

    public void remove(Long id);

    public BoardDTO getBoard(Long id);

    public List<BoardDTO> findByBoardType(String boardType);
}
