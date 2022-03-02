package com.example.board.controller;

import com.example.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String loginPage() throws Exception {
        return "board/bLogin";
    }

    // 로그아웃 페이지
    @RequestMapping(value = "/memberLogout", method = RequestMethod.GET)
    public String LogoutPage() throws Exception {
        return "board/bLogout";
    }



}
