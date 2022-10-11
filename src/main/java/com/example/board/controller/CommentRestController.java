package com.example.board.controller;
import com.example.board.service.CommentService;
import com.example.board.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    // 댓글 삽입
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public int commentInsert(CommentVo cv, @RequestParam(value="boardNum")int boardNum) {
        return commentService.commentInsert(cv, boardNum);
    }

    // 댓글 리스트
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public List<CommentVo> getCommentList(int boardNum) throws Exception {
        return commentService.commentList(boardNum);
    }

    // 댓글 수정
    @RequestMapping(value = "/comment", method = RequestMethod.PUT)
    public int updateComment(CommentVo cv) throws Exception {
        return commentService.updateComment(cv);
    }

    // 댓글 삭제
    @RequestMapping(value = "/comment", method = RequestMethod.DELETE)
    public int deleteComment(int commentNum, @RequestParam(value="boardNum")int boardNum) {
        return commentService.deleteComment(commentNum, boardNum);
    }

}
