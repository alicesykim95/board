package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.service.FileService;
import com.example.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;
    private final FileService fileService;

    // 게시글 전체 페이지 목록
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public List<BoardVo> getListBoard() throws Exception {
        return boardService.selectBoardListNone();
    }

    // 게시글 작성 처리
    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public Map<String, Object> addBoard(@RequestBody Map<String, Object> params) throws Exception {

         boardService.insertBoard(params);
         fileService.insertBoardNum(params);
         return params;
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
        return boardService.deleteBoard(boardNum);
    }


}
