package com.example.board.controller;

import com.example.board.vo.Criteria;
import com.example.board.service.BoardService;
import com.example.board.vo.BoardVo;
import com.example.board.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

//    @CrossOrigin(value = "*")
    @RequestMapping(value = "/boardListPage", method = RequestMethod.GET)
    public String openBoardList(Criteria criteria, Model model) throws Exception {

        // Access-Control-Allow-Origin : '*'
        // AJAX - json 대신  www-form-urlencoded 형식으로 보내면된다 .
        System.out.println("INIT");
        int boardtotalCount = boardService.totalRecordCount();
        Paging paging = new Paging(criteria, boardtotalCount);


        System.out.println(paging.getFirstPage());
        System.out.println(paging.isHasNextPage());

        model.addAttribute("paging", paging);
        model.addAttribute("list", boardService.selectBoardList(criteria));

        return "board/bList";
    }

    @RequestMapping(value = "boardListTable", method = RequestMethod.GET)
    public String boardListTable() throws Exception {

        return "board/Datatables";
    }

    @RequestMapping(value = "/boardWritePage", method = RequestMethod.GET)
    public String addBoardPage() throws Exception {
        return "board/bWrite";
    }

    @RequestMapping(value = "/{boardNum}", method = RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardNum") int boardNum) throws Exception {
        ModelAndView mv = new ModelAndView("board/bDetail");
        BoardVo board = boardService.getBoardDetail(boardNum);
        mv.addObject("board", board);
        return mv;
    }

}
