package com.example.board.mapper;

import com.example.board.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 댓글 목록
    List<CommentVo> commentList(int boardNum) throws Exception;

    // 댓글 작성
    int commentInsert(CommentVo cv) throws Exception;

    // 댓글 수정
    int updateComment(CommentVo cv) throws Exception;

    // 댓글 삭제
    int deleteComment(int commentNum) throws Exception;
}
