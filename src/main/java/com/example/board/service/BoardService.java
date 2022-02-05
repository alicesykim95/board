package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import com.example.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService{

    @Autowired
    private BoardMapper boardMapper;

    public List<BoardVo> selectBoardList() throws Exception{
        return boardMapper.selectBoardList();
    }

    public int insertBoard(BoardVo bdv) throws Exception {

        return boardMapper.insertBoard(bdv);
    }

    public BoardVo getBoardDetail(int boardNum) throws Exception{
        return boardMapper.getBoardDetail(boardNum);
    }

    public int updateBoard(BoardVo bdv) throws Exception{
       return boardMapper.updateBoard(bdv);
    }

    public int deleteBoard(int boardNum) throws Exception{
        return boardMapper.deleteBoard(boardNum);
    }


}
