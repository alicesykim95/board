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
    public List<CommentVo> commentList() throws Exception{
        return commentMapper.commentList();
    }

    // 댓글 등록
    public int commentInsert(CommentVo cv) throws Exception {
        return commentMapper.commentInsert(cv);
    }

}
