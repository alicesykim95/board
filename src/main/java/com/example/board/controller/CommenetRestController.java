package com.example.board.controller;

import com.example.board.service.CommentService;
import com.example.board.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommenetRestController {

    @Autowired
    CommentService commentService;

    // 댓글 삽입
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    private int commentInsert(CommentVo cv) throws Exception{
        return commentService.commentInsert(cv);
    }

}
