package com.chunjae.test04.ctrl;

import com.chunjae.test04.biz.UserService;
import com.chunjae.test04.entity.Euser;
import com.chunjae.test04.exception.NoSuchDataException;
import com.chunjae.test04.util.EmailSocket;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//GET/POST/DELETE/PUT 1개 이상씩 작성
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //회원목록 API
    @GetMapping("/userList.do")
    @ResponseBody
    public List<Euser> getUserList(Model model){
        List<Euser> userList = userService.getUserList();
        if(userList.isEmpty()){
            throw new NoSuchDataException("No Such List");
        }
        model.addAttribute("msg", "회원목록을 로딩합니다.");
        model.addAttribute("userList", userList);
        return userList;
    }

    //회원정보 API
    @GetMapping("/user.do")
    @ResponseBody
    public Euser getUser(@RequestParam("name") String name, HttpSession session, Model model){
        Euser user = userService.getUser(name);
        if(user==null){
            throw new NoSuchDataException("No Such Data");
        }
        model.addAttribute("user", user);
        return user;
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
    @ResponseBody
    public Euser loginByEmailPro(Euser euser, HttpSession session, Model model){
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
        return user;
    }

    //아이디 로그인
    @PostMapping("/loginByName.do")
    @ResponseBody
    public Euser loginByNamePro(@RequestParam("name") String name,@RequestParam("password") String password, HttpSession session, Model model){
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
        return user;
    }

    //탈퇴
    @PutMapping("/withdraw.do")
    @ResponseBody
    public int userDel(@RequestParam("name") String name, Model model){
        int cnt = userService.getWithdraw(name);
        return cnt;
    }

    //계정 삭제
    @DeleteMapping("/removeUser.do/{name}")
    @ResponseBody
    public int removeUser(@PathVariable("name") String name, Model model){
        int cnt = userService.removeUser(name);
        if(cnt == 0){
            throw new NoSuchDataException("No Delete Process Data");
        }
        return cnt;
    }

    //계정 활성화
    @PutMapping("/activate.do")
    @ResponseBody
    public int userActivate(@RequestParam("name") String name, Model model){
        int cnt = userService.getActivate(name);
        return cnt;
    }

    //휴면 처리
    @PutMapping("/dormant.do")
    @ResponseBody
    public int userDormant(@RequestParam("name") String name, Model model){
        int cnt = userService.getDormant(name);
        return cnt;
    }

    //아이디 찾기
    @PostMapping("/findId.do")
    @ResponseBody
    public Euser findById(Euser euser, Model model){
        Euser user = userService.findById(euser.getEmail(), euser.getTel());
        return user;
    }

    //비밀번호 찾기
    @PostMapping("/findPw.do")
    @ResponseBody
    public Euser findByPw(Euser euser, Model model){
        Euser user = userService.findByPw(euser.getEmail(), euser.getTel(), euser.getName());
        sendMail(euser);
        return user;
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
    @ResponseBody
    public int joinPro(Euser euser, Model model){
        int cnt = userService.userJoin(euser);
        if(cnt == 0){
            throw new NoSuchDataException("No Insert Process Data");
        }
        return cnt;
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
    public String updateFormLoad(@RequestParam("name") String name, Model model){
        Euser user = userService.getUser(name);
        model.addAttribute("msg","회원정보를 수정하실 수 있습니다.");
        model.addAttribute("user", user);
        return "user/updateUser";
    }

    @PutMapping("/updateUserPro.do")
    @ResponseBody
    public int updateUserPro(Euser user, Model model){
        int cnt = userService.updateUser(user);
        if(cnt == 0){
            throw new NoSuchDataException("No Update Process Data");
        }
        model.addAttribute("msg","회원정보를 수정하였습니다.");
        return cnt;
    }

    //회원등급변경(관리자)
    @PutMapping("/upgradeLevel.do")
    @ResponseBody
    public int upgradeLevel(@RequestParam("name") String name, @RequestParam("lev") String lev, HttpSession session, Model model){
        int cnt = userService.updateLevel(name, lev);
        model.addAttribute("msg", "등급을 변경하였습니다.");
        return cnt;
    }

    @GetMapping("/logout.do")
    public String logout(HttpSession session, Model model){
        session.invalidate();
        model.addAttribute("msg", "로그아웃 되었습니다.");
        return "index";
    }
}
