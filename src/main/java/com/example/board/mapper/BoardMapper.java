package com.example.board.mapper;
import com.example.board.vo.BoardVo;
import com.example.board.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVo> selectBoardList(Criteria criteria) throws Exception;
    List<BoardVo> selectBoardListNone() throws Exception;
    int totalRecordCount() throws Exception;
    int insertBoard(BoardVo bdv) throws Exception;
    BoardVo getBoardDetail(int boardNum) throws Exception;
    int updateBoard(BoardVo bdv) throws Exception;
    int deleteBoard(int boardNum) throws Exception;
    void updateHitCount(int boardNum) throws Exception;
}
