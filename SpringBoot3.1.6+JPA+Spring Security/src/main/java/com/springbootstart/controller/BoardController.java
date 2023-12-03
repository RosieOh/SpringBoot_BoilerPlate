package com.springbootstart.controller;

import com.springbootstart.dto.page.PageRequestDTO;
import com.springbootstart.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        
    }
}
