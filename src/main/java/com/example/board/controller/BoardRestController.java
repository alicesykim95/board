package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.service.MemberService;
import com.example.board.vo.BoardVo;
import com.example.board.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class BoardRestController {

    // 자원 = uri, 행위 = method, 표현 = JSON, XML

    // GET, POST , PUT =(PATCH), DELETE
    // 조회, 삽입, 전체 변경=(일부 변경), 삭제

    @Autowired
    private BoardService boardService;


    @Autowired
    private MemberService memberService;

    // 생성자로 호출해서 사용하는것이기 때문에 유지보수에 더 용이하다. 수정이 쉽고 빠름.
    // 그러나 차이는 별로 없다.
//    private final BoardService boardService;
//
//    @Autowired
//    public BoardRestController( BoardService boardService) {
//        this.boardService = boardService;
//    }

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public List<BoardVo> getListBoard() throws Exception {
        return boardService.selectBoardListNone();
    }

    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public int addBoard(BoardVo bdv) throws Exception {
        return boardService.insertBoard(bdv);
    }

    @RequestMapping(value = "/board", method = RequestMethod.PUT)
    public int updateBoard(BoardVo bdv) throws Exception {
        return boardService.updateBoard(bdv);
    }

    @RequestMapping(value = "/board/{id}", method = RequestMethod.GET)
    public BoardVo getOneBoard(@PathVariable("id") int boardNum) throws Exception {
        return boardService.getBoardDetail(boardNum);
    }

    @RequestMapping(value = "/board", method = RequestMethod.DELETE)
    public int deleteBoard(int boardNum) throws Exception {
        System.out.println(boardNum);
        return boardService.deleteBoard(boardNum);
    }


    // 로그인 처리
    @RequestMapping(value = "/memberLogin", method = RequestMethod.POST)
    public int login(MemberVo mbv, HttpServletRequest request, Model model) throws Exception {

        HttpSession session = request.getSession();
        MemberVo login = memberService.loginMember(mbv);

        if( login != null) {
            session.setAttribute("login", login);
            session.setMaxInactiveInterval(60 * 30);
            model.addAttribute("login", login);
            return 1;
        } else {
            return 0;
        }

    }
}
