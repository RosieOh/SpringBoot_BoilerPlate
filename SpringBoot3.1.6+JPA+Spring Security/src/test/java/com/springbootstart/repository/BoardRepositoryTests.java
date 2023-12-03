package com.springbootstart.repository;

import com.springbootstart.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

//    @Test
//    public void testInsert() {
//        IntStream.rangeClosed(1, 100).forEach(i -> {
//            Board board = Board.builder()
//                    .title("title..." + i)
//                    .content("content..." + i)
//                    .writer("user" + (i % 10))
//                    .build();
//
//            Board result = boardRepository.save(board);
//        });
//    }

    // select 기능 테스트
    @Test
    public void testSelect() {
        Long bno = 100L;

        Optional<Board> result =boardRepository.findById(bno);

        Board board = result.orElseThrow();

    }

    @Test
    public void testSearch1() {
        // 2 page order by bno desc
        Pageable pageable = PageRequest.of(1, 10,  Sort.by("bno").descending());

        boardRepository.search1(pageable);
    }

    @Test
    public void testSearchAll() {
        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
    }
}
