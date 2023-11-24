package com.shop.controller;

import com.shop.entity.User;
import com.shop.service.UserService;
import com.shop.util.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/join")
    public String joinForm(Model model, User user){
        model.addAttribute("user", user);
        return "member/joinForm";
    }

    @PostMapping("/joinPro")
    public String joinPro(User user, Integer roleId, Model model){
        userService.save(user, 4);
        model.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model, LoginForm loginForm){
        model.addAttribute("loginForm", loginForm);
        return "member/login";
    }

    /*
    @PostMapping("/auth")
    public String loginPro(User user, HttpSession session, Model model){
        User result = userService.loginPro(user);
        if(result!=null){
            session.setAttribute("userSid", result);
            model.addAttribute("user", user);
        } else {
            session.invalidate();
        }
        return "redirect:/";
    } */
}
