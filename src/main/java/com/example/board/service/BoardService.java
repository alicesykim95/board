package com.example.board.service;

import com.example.board.vo.BoardVo;

import java.util.List;

public interface BoardService {
    List<BoardVo> selectBoardList() throws Exception;
    void insertBoard(BoardVo bdv) throws Exception;
    BoardVo getBoardDetail(int boardNum) throws Exception;
    void updateBoard(BoardVo bdv) throws Exception;
}
