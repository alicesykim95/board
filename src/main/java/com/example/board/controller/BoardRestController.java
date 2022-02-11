package com.example.board.controller;

import com.example.board.vo.Criteria;
import com.example.board.service.BoardService;
import com.example.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardRestController {

    // 자원 = uri, 행위 = method, 표현 = JSON, XML

    // GET, POST , PUT =(PATCH), DELETE
    // 조회, 삽입, 전체 변경=(일부 변경), 삭제

    @Autowired
    private BoardService boardService;

//    @RequestMapping(value = "/boardList", method = RequestMethod.GET)
//    public List<BoardVo> getListBoard() throws Exception {
//        return boardService.selectBoardList();
//    }

    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public int addBoard(BoardVo bdv) throws Exception {
        return boardService.insertBoard(bdv);
    }

    @RequestMapping(value = "/board", method = RequestMethod.PUT)
    public int updateBoard(BoardVo bdv) throws Exception {
        return boardService.updateBoard(bdv);
    }

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public BoardVo getOneBoard(int boardNum) throws Exception {
        return boardService.getBoardDetail(boardNum);
    }

    @RequestMapping(value = "/board", method = RequestMethod.DELETE)
    public int deleteBoard(int boardNum) throws Exception {
        System.out.println(boardNum);
        return boardService.deleteBoard(boardNum);
    }

}
