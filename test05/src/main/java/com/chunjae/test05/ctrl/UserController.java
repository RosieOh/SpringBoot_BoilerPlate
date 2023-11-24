package com.chunjae.test05.ctrl;

import com.chunjae.test05.biz.UserService;
import com.chunjae.test05.entity.Euser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8085")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/")
    public String home(Model model){
        return "index";
    }

    @GetMapping("/admin/userList")
    public String getUserList(Model model) { // User 테이블의 전체 정보를 보여줌
        List<Euser> userList = userService.getUserList();
        model.addAttribute("list", userList);
        return "admin/list";
    }

    @GetMapping("/admin/get")
    public String getUser(@RequestParam("name") String name, Model model) { // User 테이블의 전체 정보를 보여줌
        Euser user = userService.getUser(name);
        model.addAttribute("user", user);
        return "admin/get";
    }

    @GetMapping("/user")
    public String getUserInfo(@RequestParam("id") Integer id, Model model) { // User 테이블의 전체 정보를 보여줌
        // token에 저장되어 있는 인증된 사용자의 id 값
        Euser user = userService.getUserById(id);
        user.setPassword(null); // password는 보이지 않도록 null로 설정
        model.addAttribute("user", user);
        return "user/get";
    }

    @GetMapping("/login")
    public String loginPage() { // 로그인되지 않은 상태이면 로그인 페이지를, 로그인된 상태이면 home 페이지를 보여줌
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "login";
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupPage() {  // 회원 가입 페이지
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "join";
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signup(Euser userVo) { // 회원 가입
        try {
            userService.userJoin(userVo);
        } catch (DuplicateKeyException e) {
            return "redirect:/signup?error_code=-1";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/signup?error_code=-99";
        }
        return "redirect:/login";
    }

    @GetMapping("/update")
    public String editPage(@RequestParam("name") String name, Model model) { // 회원 정보 수정 페이지
        Euser userVo = userService.getByName(name);
        model.addAttribute("user", userVo);
        return "user/updateUser";
    }

    @PostMapping("/update")
    public String edit(Euser user) { // 회원 정보 수정
        Euser euser = userService.getUserById(user.getId());
        int cnt = 0;
        if(user.getPassword().equals(euser.getPassword())){
            cnt = userService.updatePasswordNoChange(user);
        } else {
            cnt = userService.updateUser(user);
        }
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String withdraw(@RequestParam("name") String name, HttpSession session) { // 회원 탈퇴
        if (name != null) {
            userService.getWithdraw(name);
        }
        SecurityContextHolder.clearContext(); // SecurityContextHolder에 남아있는 token 삭제
        return "redirect:/";
    }

    //중복 id 검증(Ajax)
    @PostMapping("/idCheck")
    @ResponseBody
    public boolean idCheckAjax(@RequestParam("name") String name){
        boolean result = false;
        Euser user = userService.getByName(name);
        if(user!=null){
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    //중복 이메일 검증(Ajax)
    @PostMapping("/emailCheck")
    @ResponseBody
    public boolean emailCheckAjax(@RequestParam("email") String email){
        Euser user = userService.getByEmail(email);
        if(user!=null){
            return false;
        } else {
            return true;
        }
    }
}
