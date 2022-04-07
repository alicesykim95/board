package com.example.board.controller;

import com.example.board.service.UserService;
import com.example.board.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 메인 페이지
     @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
     public String home() {
        return "board/bHome";
     }

    // 회원가입 페이지
    @RequestMapping(value = "/userJoinPage", method = RequestMethod.GET)
    public String addMemeberPage() {
        return "board/bJoin";
    }

    // 회원가입 처리
    @RequestMapping(value = "/userJoin", method = RequestMethod.POST)
    @ResponseBody
    public int addMember(UserVo uv) throws Exception {
        return userService.insertMember(uv);
    }

}
