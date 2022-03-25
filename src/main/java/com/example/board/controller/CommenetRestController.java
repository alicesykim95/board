package com.example.board.controller;

import com.example.board.service.CommentService;
import com.example.board.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommenetRestController {

    @Autowired
    CommentService commentService;

    // 댓글 삽입
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public int commentInsert(CommentVo cv) throws Exception{

        return commentService.commentInsert(cv);
    }

    // 댓글 리스트
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public List<CommentVo> getCommentList() throws Exception {
        return commentService.commentList();
    }

    // 댓글 수정
    @RequestMapping(value = "/comment", method = RequestMethod.PUT)
    public int updateComment(CommentVo cv) throws Exception {
        return commentService.updateComment(cv);
    }

}
