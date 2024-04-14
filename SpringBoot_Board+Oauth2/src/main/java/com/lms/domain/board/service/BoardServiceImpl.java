package com.lms.domain.board.service;


import com.lms.domain.board.dto.BoardDTO;
import com.lms.domain.board.entity.Board;
import com.lms.domain.board.repository.BoardRepository;
import com.lms.global.cosntant.BoardType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    // 새로운 게시판을 추가해주는 메소드
    public void createAndSaveBoards() {
        List<Board> board = new ArrayList<>();

        // 인스펙션 필요
        Board noticeBoard = new Board();
        noticeBoard.setTitle("공지사항");
        noticeBoard.setContent("내용");
        noticeBoard.setBoardType(BoardType.NOTICE.toString());
        board.add(noticeBoard);

        Board modifyBoard = new Board();
        modifyBoard.setTitle("수정요청");
        modifyBoard.setContent("내용");
        modifyBoard.setBoardType(BoardType.MODIFY.toString());
        board.add(modifyBoard);


        // 생성된 게시판들을 저장
        boardRepository.saveAll(board);
    }
    @Override
    public BoardDTO findById(Long id) {
        Optional<Board> result = boardRepository.findById(id);
        BoardDTO boardDTO = modelMapper.map(result, BoardDTO.class);
        return boardDTO;
    }

    @Override
    public List<BoardDTO> findAll(BoardDTO boardDTO) {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for(Board board1 : boardList) {
            Board board = Board.builder()
                    .id(boardDTO.getId())
                    .title(boardDTO.getTitle())
                    .content(boardDTO.getContent())
                    .boardType(boardDTO.getBoardType())
                    .fileId(boardDTO.getFileId())
                    .build();
            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }

    @Override
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    @Override
    public List<BoardDTO> findByBoardType(String boardType) {
        List<Board> lst = boardRepository.findByBoardType(boardType);

        if (lst != null && !lst.isEmpty()) {
            List<BoardDTO> boardList = lst.stream().map(board -> modelMapper.map(board, BoardDTO.class))
                    .collect(Collectors.toList());
            return boardList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void register(BoardDTO boardDTO) {

        Board board = Board.builder()
                .id(boardDTO.getId())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .boardType(boardDTO.getBoardType())
                .writer(boardDTO.getWriter())
                .fileId(boardDTO.getFileId())
                .build();
        boardRepository.save(board);
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);
    }


    @Override
    public BoardDTO getBoard(Long id ) {
        Optional<Board> result = boardRepository.findById(id);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;
    }



    @Override
    public void remove(Long id) {
        boardRepository.deleteById(id);
    }
}
