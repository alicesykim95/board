package com.example.board.controller;

import com.example.board.service.MemberService;
import com.example.board.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public int addMember(MemberVo mbv) throws Exception{
        return memberService.insertMember(mbv);
    }
}
