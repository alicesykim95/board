package com.example.board.controller;

import com.example.board.vo.Criteria;
import com.example.board.service.BoardService;
import com.example.board.vo.BoardVo;
import com.example.board.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping(value = "/boardListPage", method = RequestMethod.GET)
    public String openBoardList(Criteria criteria, Model model) throws Exception {

        int boardtotalCount = boardService.totalRecordCount();
        Paging paging = new Paging(criteria, boardtotalCount);

        model.addAttribute("paging", paging);
        model.addAttribute("list", boardService.selectBoardList(criteria));

        return "board/bList";
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


//ksy
//
//
//ksy1010%%