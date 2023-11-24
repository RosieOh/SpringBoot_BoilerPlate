package com.chunjae.test03.ctrl;

import com.chunjae.test03.biz.UserService;
import com.chunjae.test03.entity.Euser;
import com.chunjae.test03.util.EmailSocket;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //회원목록(관리자)
    @GetMapping("/userList.do")
    public String getUserList(HttpSession session, Model model){
        String slevel = (String) session.getAttribute("slevel");
        if(slevel.equals("ADMIN")) {
            List<Euser> userList = userService.getUserList();
            model.addAttribute("msg", "회원목록을 로딩합니다.");
            model.addAttribute("userList", userList);
            return "user/list";
        } else {
            model.addAttribute("msg", "해당 권한이 없습니다.");
            return "redirect:err/permission";
        }
    }

    //회원정보(로그인한 사용자만 정보 조회 - 일반회원+직원+관리자)
    @GetMapping("/user.do")
    public String getUser(@RequestParam("name") String name, HttpSession session, Model model){
        String sname = (String) session.getAttribute("sname");
        if(sname!=null) {
            Euser user = userService.getUser(name);
            model.addAttribute("user", user);
            return "user/get";
        } else {
            return "redirect:/";
        }
    }

    //로그인 폼 로딩
    @GetMapping("/login.do")
    public String login(HttpSession session, Model model){
        String sname = (String) session.getAttribute("sname");
        if(sname!=null){
            return "redirect:/";
        }
        return "user/login";
    }

    //이메일 로그인
    @PostMapping("/loginByEmail.do")
    public String loginByEmailPro(Euser euser, HttpSession session, Model model){
        Euser user = userService.getByEmail(euser.getEmail());
        if(user!=null){
            if(user.getPassword().equals(euser.getPassword())){
                model.addAttribute("msg", "로그인 성공");
                session.setAttribute("sname", user.getName());
            } else {
                model.addAttribute("msg", "비밀번호 오류 로그인 실패");
                session.invalidate();
            }
        } else {
            model.addAttribute("msg", "해당 이메일을 가진 회원이 존재하지 않습니다.");
            session.invalidate();
        }
        return "index";
    }

    //아이디 로그인
    @PostMapping("/loginByName.do")
    public String loginByNamePro(@RequestParam("name") String name,@RequestParam("password") String password, HttpSession session, Model model){
        Euser user = userService.getByName(name);
        if(user!=null){
            if(user.getPassword().equals(password)){
                model.addAttribute("msg", "로그인 성공");
                session.setAttribute("sname", user.getName());
                session.setAttribute("slevel", user.getLev());
            } else {
                model.addAttribute("msg", "비밀번호 오류 로그인 실패");
                session.invalidate();
            }
        } else {
            model.addAttribute("msg", "해당 아이디를 가진 회원이 존재하지 않습니다.");
            session.invalidate();
        }
        return "index";
    }

    //탈퇴
    @GetMapping("/withdraw.do")
    public String userDel(@RequestParam("name") String name, Model model){
        userService.getWithdraw(name);
        return "redirect:/";
    }

    //계정 활성화
    @GetMapping("/activate.do")
    public String userActivate(@RequestParam("name") String name, Model model){
        userService.getActivate(name);
        return "redirect:/";
    }

    //휴면 처리
    @GetMapping("/dormant.do")
    public String userDormant(@RequestParam("name") String name, Model model){
        userService.getDormant(name);
        return "redirect:/";
    }

    //아이디 찾기
    @PostMapping("/findId.do")
    public String findById(Euser euser, Model model){
        Euser user = userService.findById(euser.getEmail(), euser.getTel());
        return "user/findId";
    }

    //비밀번호 찾기
    @PostMapping("/findPw.do")
    public String findByPw(Euser euser, Model model){
        Euser user = userService.findByPw(euser.getEmail(), euser.getTel(), euser.getName());
        sendMail(euser);
        return "user/findPw";
    }

    public void sendMail(Euser euser){
        EmailSocket dm = new EmailSocket();
        dm.sendMail(euser);
    }

    //회원가입 폼 로딩
    @GetMapping("/join.do")
    public String joinFormLoad(HttpSession session, Model model){
        String sname = (String) session.getAttribute("sname");
        if(sname!=null){
            model.addAttribute("msg", "현재 로그인이 되어 있으므로 회원가입이 허용되지 않습니다.");
            return "index";
        }
        model.addAttribute("msg", "회원가입 정보를 입력하시기 바랍니다.");
        return "user/join";
    }


    //회원가입 처리
    @PostMapping("/joinPro.do")
    public String joinPro(Euser euser, Model model){
        userService.userJoin(euser);
        return "redirect:login.do";
    }

    //중복 id 검증(Ajax)
    @PostMapping("idCheck.do")
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
    @PostMapping("emailCheck.do")
    @ResponseBody
    public boolean emailCheckAjax(@RequestParam("email") String email){
        Euser user = userService.getByEmail(email);
        if(user!=null){
            return false;
        } else {
            return true;
        }
    }

    //회원정보수정 폼 로딩
    @GetMapping("/updateForm.do")
    public String updateFormLoad(HttpSession session, Model model){
        String sname = (String) session.getAttribute("sname");
        if(sname!=null) {
            Euser user = userService.getUser(sname);
            model.addAttribute("msg","회원정보를 수정하실 수 있습니다.");
            model.addAttribute("user", user);
            return "user/updateUser";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/updateUserPro.do")
    public String updateUserPro(Euser user, Model model){
        userService.updateUser(user);
        model.addAttribute("msg","회원정보를 수정하였습니다.");
        return "index";
    }

    //회원등급변경(관리자)
    @GetMapping("/upgradeLevel.do")
    public String upgradeLevel(@RequestParam("name") String name, @RequestParam("lev") String lev, HttpSession session, Model model){
        String slevel = (String) session.getAttribute("slevel");
        if(slevel.equals("ADMIN")) {
            userService.updateLevel(name, lev);
            model.addAttribute("msg", "등급을 변경하였습니다.");
            return "index";
        } else {
            model.addAttribute("msg", "해당 권한이 없습니다.");
            return "redirect:err/permission";
        }
    }

    @GetMapping("/logout.do")
    public String logout(HttpSession session, Model model){
        session.invalidate();
        model.addAttribute("msg", "로그아웃 되었습니다.");
        return "index";
    }
}
