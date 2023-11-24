package com.shop.controller;


import com.shop.dto.MemberJoinDTO;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinGET(){
        log.info("join get...");
        return "member/join";
    }

    @PostMapping("/join")
    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){
        log.info("join post...");
        log.info(memberJoinDTO);
        try {
            memberService.join(memberJoinDTO);
        } catch (MemberService.MidExistException e) {

            redirectAttributes.addFlashAttribute("error", "mid");
            return "redirect:/member/join";
        }
        redirectAttributes.addFlashAttribute("result", "success");
        return "redirect:/member/login"; //회원 가입 후 로그인
    }

    @GetMapping("/login")
    public String loginGet(){
        log.info("/login get ...........");
        return "member/login";
    }

    @PreAuthorize("hasRole('USER')") //로그인하여 USER 롤이 있는 경우만
    @GetMapping("/mypage")
    public String myPage(Principal principal, Model model){
        String mid = principal.getName();
        MemberJoinDTO memberDto = memberService.myinfo(mid);
        log.info("----- MyInfo -----");
        log.info(memberDto);
        model.addAttribute("memberDto",memberDto);
        return "member/mypage";
    }
}
