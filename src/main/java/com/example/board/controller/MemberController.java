package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
    @RequestMapping(value = "/memberJoin", method = RequestMethod.GET)
    public String addMemeberPage() throws Exception {
        return "board/bJoin";
    }
}
