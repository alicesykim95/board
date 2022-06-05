package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import com.example.board.mapper.CommentMapper;
import com.example.board.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;
    private final BoardMapper boardMapper;

    // 댓글 리스트
    public List<CommentVo> commentList(int boardNum) throws Exception {

        return commentMapper.commentList(boardNum);
    }

    // 댓글 등록 및 댓글 갯수 업데이트
    public int commentInsert(CommentVo cv, int boardNum) {
        try {
            commentMapper.commentInsert(cv);
            boardMapper.updateCommentCount(boardNum);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    // 댓글 수정
    public int updateComment(CommentVo cv) throws Exception {
        return commentMapper.updateComment(cv);
    }

    // 댓글 삭제 및 댓글 갯수 업데이트
    public int deleteComment(int commentNum, int boardNum) {
        try {
            commentMapper.deleteComment(commentNum);
            boardMapper.updateCommentCount(boardNum);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

}
