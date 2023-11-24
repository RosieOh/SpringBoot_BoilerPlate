package com.chunjae.test07.ctrl;

import com.chunjae.test07.biz.UserService;
import com.chunjae.test07.domain.UserPrincipal;
import com.chunjae.test07.entity.Human;
import com.chunjae.test07.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping( "/")
    public String home(Model model){
        Human human = new Human();
        human.setName("김천재");
        human.setAge(39);

        List<Human> hList = new ArrayList<>();

        for(int i=0;i<4;i++){
            Human h = new Human();
            h.setName("김나연"+i);
            h.setAge(i+25);
            hList.add(h);
        }
        model.addAttribute("hList", hList);
        model.addAttribute("human", human);
        model.addAttribute("attrName", "임의필드");
        return "/user/index";
    }

    @GetMapping( "/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/user/registration");

        return modelAndView;
    }

    //@Valid
    @PostMapping("/registration")
    public ModelAndView createNewUser(User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        User userExists = userService.findUserByLoginId(user.getLoginId());
        if (userExists != null) {
            bindingResult
                    .rejectValue("loginId", "error.loginId","There is already a user registered with the loginId provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/user/registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("/user/registration");
        }
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        System.out.println(userPrincipal.toString());

        modelAndView.addObject("userName", "Welcome " + userPrincipal.getName() + " (" + userPrincipal.getId() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("/user/home");
        return modelAndView;
    }

    @GetMapping("/exception")
    public ModelAndView getUserPermissionExceptionPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/access-denied");
        return mv;
    }
}
