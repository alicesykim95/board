package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import com.example.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardVo> selectBoardList() throws Exception{
        return boardMapper.selectBoardList();
    }

    @Override
    public void insertBoard(BoardVo bdv) throws Exception {
        boardMapper.insertBoard(bdv);
    }

    @Override
    public BoardVo getBoardDetail(int boardNum) throws Exception{
        BoardVo board = boardMapper.getBoardDetail(boardNum);
        return board;
    }

    @Override
    public void updateBoard(BoardVo bdv) throws Exception{
        boardMapper.updateBoard(bdv);
    }


}
