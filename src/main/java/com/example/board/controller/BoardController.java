package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/boardList", method = RequestMethod.GET)
    public ModelAndView openBoardList() throws Exception{
        ModelAndView mv = new ModelAndView("board/bList");
        List<BoardVo> list = boardService.selectBoardList();
        mv.addObject("list", list);
        return  mv;
    }

    @RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
    public String addBoard() throws Exception{
        return "board/bWrite";
    }

    @RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
    public String addBoard(BoardVo bdv) throws Exception{
        boardService.insertBoard(bdv);
        return "redirect:boardList";
    }

    @RequestMapping(value = "/{boardNum}", method = RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardNum") int boardNum)throws Exception{
        ModelAndView mv = new ModelAndView("board/bDetail");
        BoardVo board = boardService.getBoardDetail(boardNum);
        mv.addObject("board", board);
        return mv;
    }

    @RequestMapping(value = "boardUpdate")
    public String updateBoard(BoardVo bdv) throws Exception{
        boardService.updateBoard(bdv);
        return "redirect:/boardList";
    }


}
