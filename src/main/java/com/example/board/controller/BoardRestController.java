package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.service.UserService;
import com.example.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private UserService memberService;

    // 생성자로 호출해서 사용하는것이기 때문에 유지보수에 더 용이하다. 수정이 쉽고 빠름.
    // 그러나 차이는 별로 없다.
    // private final BoardService boardService;
    //
    // @Autowired
    // public BoardRestController( BoardService boardService) {
    //     this.boardService = boardService;
    // }

    // 게시글 전체 페이지 목록
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public List<BoardVo> getListBoard() throws Exception {
        return boardService.selectBoardListNone();
    }

    // 게시글 작성 처리
    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public int addBoard(BoardVo bdv) throws Exception {
        return boardService.insertBoard(bdv);
    }

    // 게시글 수정 처리
    @RequestMapping(value = "/board", method = RequestMethod.PUT)
    public int updateBoard(BoardVo bdv) throws Exception {
        return boardService.updateBoard(bdv);
    }

    // 게시글 상세 목록
    @RequestMapping(value = "/board/{id}", method = RequestMethod.GET)
    public BoardVo getOneBoard(@PathVariable("id") int boardNum) throws Exception {
        return boardService.getBoardDetail(boardNum);
    }

    // 게시글 삭제 처리
    @RequestMapping(value = "/board", method = RequestMethod.DELETE)
    public int deleteBoard(int boardNum) throws Exception {
        System.out.println(boardNum);
        return boardService.deleteBoard(boardNum);
    }


}
