package com.example.board.service;

import com.example.board.mapper.CommentMapper;
import com.example.board.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    // 댓글 리스트
    public List<CommentVo> commentList(int boardNum) throws Exception{
        return commentMapper.commentList(boardNum);
    }

    // 댓글 등록
    public int commentInsert(CommentVo cv) throws Exception {
        return commentMapper.commentInsert(cv);
    }

    // 댓글 수정
    public int updateComment(CommentVo cv) throws Exception{
        return commentMapper.updateComment(cv);
    }

    // 댓글 삭제
    public int deleteComment(int commentNum) throws Exception{
        return commentMapper.deleteComment(commentNum);
    }

}
