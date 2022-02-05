package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;


    @RequestMapping(value = "/boardListPage", method = RequestMethod.GET)
    public String openBoardList() throws Exception{
        return "board/bList";
    }

    @RequestMapping(value = "/boardWritePage", method = RequestMethod.GET)
    public String addBoardPage() throws Exception{
        return "board/bWrite";
    }

    @RequestMapping(value = "/{boardNum}", method = RequestMethod.GET)
    public String openBoardDetail(@PathVariable("boardNum") int boardNum, Model model)throws Exception{
        return "board/bDetail";
    }

}
