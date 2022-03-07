package com.example.board.controller;

import com.example.board.service.MemberService;
import com.example.board.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @RequestMapping(value = "/memberJoinPage", method = RequestMethod.GET)
    public String addMemeberPage() throws Exception {
        return "board/bJoin";
    }

}
