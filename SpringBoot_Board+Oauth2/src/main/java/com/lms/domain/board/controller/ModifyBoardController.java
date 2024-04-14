package com.lms.domain.board.controller;

import com.lms.domain.board.dto.BoardDTO;
import com.lms.domain.board.service.BoardService;
import com.lms.domain.file.dto.FileDTO;
import com.lms.domain.file.service.FileService;
import com.lms.domain.member.entity.Member;
import com.lms.domain.member.repository.MemberRepository;
import com.lms.domain.member.service.MemberService;
import com.lms.global.util.MD5Generator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/modifyBoard")
public class ModifyBoardController {

    @Value("${upload.path}")
    private String uploadPath;

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final BoardService boardService;
    private final FileService fileService;

    @GetMapping(value = {"/list", "/"})
    public String modifyBoardList(Model model, Principal principal) {
        String boardType = "MODIFY";
        List<BoardDTO> boardList = boardService.findByBoardType(boardType);
        model.addAttribute("boardList", boardList);
        String email = principal.getName();
        Optional<Member> optionalMember = memberRepository.findByEmail2(email);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            String name = member.getName();
            model.addAttribute("name", name);
        }
        model.addAttribute("principal", principal);
        return "admin/modifyBoard/list";
    }

    @GetMapping("/read")
    public String readModifyBoard(Long id, Model model, Principal principal) {
        if (id != null) {
            BoardDTO boardDTO = boardService.findById(id);
            if (boardDTO != null) {
                FileDTO fileDTO = fileService.getFile(boardDTO.getFileId());
                String email = principal.getName();
                Optional<Member> optionalMember = memberRepository.findByEmail2(email);
                if (optionalMember.isPresent()) {
                    Member member = optionalMember.get();
                    String name = member.getName();
                    model.addAttribute("name", name);
                }
                model.addAttribute("principal", principal);
                model.addAttribute("fileList", fileDTO);
                model.addAttribute("boardList", boardDTO);
            } else {
                log.info("fileDTO" + fileService);
            }
        }
        return "admin/modifyBoard/view";
    }



    @GetMapping("/register")
    public String registerForm(Model model, Principal principal) {
        String email = principal.getName();
        Optional<Member> optionalMember = memberRepository.findByEmail2(email);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            String name = member.getName();
            model.addAttribute("name", name);
        }
        return "admin/modifyBoard/register";
    }

    @PostMapping("/register")
    public String modifyBoardRegister(@Valid BoardDTO boardDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model,
                                 @RequestParam("file") MultipartFile files) {
        try {
            String originFilename = files.getOriginalFilename();
            String filename = new MD5Generator(originFilename).toString();
            String savePath = System.getProperty("user.dir") + "/files/";
            log.info("어디로 가니?  " + savePath);
            if(!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdirs();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String filePath = savePath + filename;

            files.transferTo(new File(filePath));

            FileDTO fileDTO = new FileDTO();
            fileDTO.setOriginFileName(originFilename);
            fileDTO.setFileName(filename);
            fileDTO.setFilePath(filePath);

            Long fileId = fileService.saveFile(fileDTO);
            boardDTO.setFileId(fileId);
            boardDTO.setWriter(boardDTO.getWriter());
            boardService.register(boardDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("message", "수정 요청 글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/modifyBoard/list");
        return "admin/modifyBoard/message";
    }

    @GetMapping("/modify")
    public String modifyBoardEditForm(Model model, Long id) {
        BoardDTO boardDTO = boardService.getBoard(id);
        model.addAttribute("boardDTO", boardDTO);
        return "admin/modifyBoard/edit";
    }

    @PostMapping("/modify/{id}")
    public String modifyBoardEdit(@PathVariable("id") Long id, BoardDTO boardDTO, @RequestParam("file") MultipartFile file) {
        BoardDTO boardDTO1 = boardService.getBoard(id);
        boardDTO1.setTitle(boardDTO.getTitle());
        boardDTO1.setContent(boardDTO.getContent());

        // 새 파일이 업로드된 경우에만 처리
        if (!file.isEmpty()) {
            // 이전 파일 삭제
            List<String> filesToDelete = Arrays.asList(String.valueOf(boardDTO1.getFileId()));
            removeFiles(filesToDelete);

            // 새 파일 저장
            String fileName = storeFile(file);

            // 파일 ID 설정
            boardDTO1.setFileId(Long.valueOf(fileName));
        }

        boardService.modify(boardDTO1); // 수정된 boardDTO1을 전달
        return "redirect:/modifyBoard/read?id=" + id;
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.GET, RequestMethod.POST})
    public String remove(Long id, RedirectAttributes redirectAttributes) {
        log.info("remove post.. " + id);
        boardService.remove(id);
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/modifyBoard/list";
    }

    private void removeFiles(List<String> files) {
        for (String fileName:files) {
            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();
            try {
                String contentType = Files.probeContentType(resource.getFile().toPath());
                resource.getFile().delete();
                if (contentType.startsWith("image")) {
                    File thumbnailFile = new File(uploadPath + File.separator+"s_"+ fileName);
                    thumbnailFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }


    private String storeFile(MultipartFile file) {
        // 저장할 디렉토리 경로 설정
        String uploadDir = "/path/to/upload/directory";

        // 디렉토리가 없으면 생성
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                // 디렉토리 생성 실패 처리
                e.printStackTrace();
            }
        }

        // 파일명 생성 (중복 방지를 위해 유니크한 이름 사용)
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

        try {
            // 파일 저장
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath);

            return uniqueFileName; // 저장된 파일명 반환
        } catch (IOException e) {
            // 파일 저장 실패 처리
            e.printStackTrace();
            return null;
        }
    }
}
