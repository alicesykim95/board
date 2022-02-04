package com.example.board.mapper;

import com.example.board.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVo> selectBoardList() throws Exception;
    void insertBoard(BoardVo bdv) throws Exception;
    BoardVo getBoardDetail(int boardNum) throws Exception;
    void updateBoard(BoardVo bdv) throws Exception;
}
