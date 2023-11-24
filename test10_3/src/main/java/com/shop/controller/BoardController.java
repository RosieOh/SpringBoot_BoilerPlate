package com.shop.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.shop.dto.*;
import com.shop.service.BoardService;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    @Value("${com.shop.upload.path}")
    private String uploadPath;

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<BoardListAllDTO> responseDTO =
                boardService.listWithAll(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
	    return "board/list";
    }


    @GetMapping("/register")
    public String registerGET(){
	return "board/register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return "redirect:/board/register";
        }
        Long bno  = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result", bno);
        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(Long bno, PageRequestDTO pageRequestDTO, Model model){
        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
	    return "board/read";
    }


    @GetMapping("/modify")
    public String modifyForm(Long bno, PageRequestDTO pageRequestDTO, Model model){
        BoardDTO boardDTO = boardService.readOne(bno);
        model.addAttribute("dto", boardDTO);
	    return "board/modify";
    }



    @PostMapping("/modify")
    public String modify( @Valid BoardDTO boardDTO,
                          BindingResult bindingResult,
                          PageRequestDTO pageRequestDTO,
                          RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/modify?"+link;
        }
        boardService.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/board/read";
    }



    @PostMapping("/remove")
    public String remove(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
        Long bno  = boardDTO.getBno();
        boardService.remove(bno);
        List<String> fileNames = boardDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0){
            removeFiles(fileNames);
        }
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }


    public void removeFiles(List<String> files){
        for (String fileName:files) {
            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();
            try {
                String contentType = Files.probeContentType(resource.getFile().toPath());
                resource.getFile().delete();
                if (contentType.startsWith("image")) {                  //섬네일이 존재한다면
                    File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
                    thumbnailFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        }//end for
    }
}
