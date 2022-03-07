package com.example.board.controller;

import com.example.board.service.MemberService;
import com.example.board.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    // 로그인 페이지
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();

        Object loginYn = session.getAttribute("login");

        if (loginYn != null) {
            System.out.println(loginYn.toString());
            return "board/bHome";
        }

        return "board/bLogin";
    }

    // 로그아웃 페이지
    @RequestMapping(value = "/memberLogout", method = RequestMethod.GET)
    public String LogoutPage(HttpServletRequest request) throws Exception {

        try {
            HttpSession session = request.getSession();
            session.invalidate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "board/bHome";
    }


}

