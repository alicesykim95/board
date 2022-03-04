package com.example.board.controller;

import com.example.board.service.MemberService;
import com.example.board.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;
    // 메인 페이지
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() throws Exception{
        return "board/bHome";
    }

    // 회원가입 페이지
    @RequestMapping(value = "/memberJoin", method = RequestMethod.GET)
    public String addMemeberPage() throws Exception {
        return "board/bJoin";
    }

    // 로그인 페이지
    @RequestMapping(value = "/memberLogin", method = RequestMethod.GET)
    public String loginPage(MemberVo mbv, HttpServletRequest request, Model model) throws Exception {

        HttpSession session = request.getSession();

        MemberVo login = memberService.loginMember(mbv);

        if (login != null){
            session.setAttribute("login", login);
            session.setMaxInactiveInterval(60 * 30);
            model.addAttribute("login", login);
        } else {
            session.setAttribute("login", null);
            model.addAttribute("login", null);
        }


        return "board/bLogin";
    }

    // 로그아웃 페이지
    @RequestMapping(value = "/memberLogout", method = RequestMethod.GET)
    public String LogoutPage() throws Exception {
        return "board/bLogout";
    }



}
