package com.springbootstart.service;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.page.PageRequestDTO;
import com.springbootstart.dto.page.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("오늘은 JPA 공부했지롱")
                .content("JPA 독학하니 할만하노")
                .writer("오태훈")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno : " + bno);
    }

    @Test
    public void testModify() {
        // 변경에 필요한 데이터만
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Updated...101")
                .content("Updated content 101...")
                .build();

        boardService.modify(boardDTO);
    }

    // 목록/검색 기능 테스트
    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("admin")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }
}