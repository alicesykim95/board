package com.example.board.mapper;
import com.example.board.vo.BoardVo;
import com.example.board.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시글 전체 리스트 for 화면
    List<BoardVo> selectBoardList(Criteria criteria) throws Exception;

    // 게시글 전체 리스트 for RestApi
    List<BoardVo> selectBoardListNone() throws Exception;

    // 게시글 전체 리스트 갯수 for 페이징
    int totalRecordCount() throws Exception;

    // 게시글 전체 리스트 체크박스 삭제
    void deleteBoardList(int boardNum) throws Exception;

    // 게시글 전체 리스트 댓글 갯수
    void updateCommentCount(int boardNum) throws Exception;

    // 게시글 작성
    int insertBoard(BoardVo bdv) throws Exception;

    // 게시글 상세
    BoardVo getBoardDetail(int boardNum) throws Exception;

    // 게시글 수정
    int updateBoard(BoardVo bdv) throws Exception;

    // 게시글 삭제
    int deleteBoard(int boardNum) throws Exception;

    // 게시글 조회수
    void updateHitCount(int boardNum) throws Exception;
}
