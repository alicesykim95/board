package com.example.board.controller;

import com.example.board.service.UserService;
import com.example.board.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 메인 페이지
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() throws Exception {
        return "board/bHome";
    }

    // 회원가입 페이지
    @RequestMapping(value = "/userJoinPage", method = RequestMethod.GET)
    public String addMemeberPage() throws Exception {
        return "board/bJoin";
    }

    // 회원가입 처리
    @RequestMapping(value = "/userJoin", method = RequestMethod.POST)
    @ResponseBody
    public int addMember(UserVo uv) throws Exception {
        return userService.insertMember(uv);
    }

}
