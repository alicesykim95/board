package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import com.example.board.vo.BoardVo;
import com.example.board.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    // 게시글 전체 리스트 for 화면
    public List<BoardVo> selectBoardList(Criteria criteria) throws Exception {
        return boardMapper.selectBoardList(criteria);
    }

    // 게시글 전체 리스트 for RestApi
    public List<BoardVo> selectBoardListNone() throws Exception {
        return boardMapper.selectBoardListNone();
    }

    // 게시글 전체 리스트 갯수 for 페이징
    public int totalRecordCount(Criteria criteria) throws Exception {
        return boardMapper.totalRecordCount(criteria);
    }

    // 게시글 전체 리스트 체크 박스 삭제
    public void deleteBoardList(int boardNum) throws Exception {
        boardMapper.deleteBoardList(boardNum);
    }

    // 게시글 작성
    public void insertBoard(Map<String, Object> params) throws Exception {
        boardMapper.insertBoard(params);
    }

    // 게시글 상세
    public BoardVo getBoardDetail(int boardNum) throws Exception {
        boardMapper.updateHitCount(boardNum);
        return boardMapper.getBoardDetail(boardNum);
    }

    // 게시글 수정
    public int updateBoard(BoardVo bdv) throws Exception {
        return boardMapper.updateBoard(bdv);
    }

    // 게시글 삭제
    public int deleteBoard(int boardNum) throws Exception {
        return boardMapper.deleteBoard(boardNum);
    }


}
