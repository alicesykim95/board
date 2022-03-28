package com.example.board.controller;

import com.example.board.service.CommentService;
import com.example.board.vo.*;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final CommentService commentService;

    //@CrossOrigin(value = "*")
    // 게시글 전체 리스트 페이지
    @RequestMapping(value = "/boardListPage", method = RequestMethod.GET)
    public String openBoardList(Criteria criteria, Model model) throws Exception {

        // Access-Control-Allow-Origin : '*'
        // AJAX - json 대신  www-form-urlencoded 형식으로 보내면된다.
        System.out.println("INIT");
        int boardtotalCount = boardService.totalRecordCount();
        Paging paging = new Paging(criteria, boardtotalCount);

        System.out.println(paging.getFirstPage());
        System.out.println(paging.isHasNextPage());

        model.addAttribute("paging", paging);
        model.addAttribute("list", boardService.selectBoardList(criteria));

        return "board/bList";
    }

    // 게시글 jQuery 페이징 구현 페이지
    @RequestMapping(value = "boardListTable", method = RequestMethod.GET)
    public String boardListTable() throws Exception {
        return "board/Datatables";
    }

    // 게시글 작성 페이지
    @RequestMapping(value = "/boardWritePage", method = RequestMethod.GET)
    public String addBoardPage() throws Exception {
        return "board/bWrite";
    }

    // 게시글 상세 페이지: 게시글 수정 및 삭제 + 댓글 리스트 수정 및 삭제
    @RequestMapping(value = "/{boardNum}", method = RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardNum") int boardNum, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        UserVo userVo = (UserVo) session.getAttribute("login");

        ModelAndView mv = new ModelAndView("board/bDetail");

        BoardVo board = boardService.getBoardDetail(boardNum);

        // 게시판 상세 내용
        mv.addObject("board", board);
        // 게시판 댓글 리스트
        mv.addObject("comment", commentService.commentList(boardNum));
        // 로그인중인 아이디 가져오기
        mv.addObject("login", userVo);

        return mv;
    }

}
