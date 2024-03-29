package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.service.FileService;
import com.example.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;
    private final FileService fileService;

    // 게시글 작성 처리
    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public BoardVo addBoard(BoardVo bdv, @RequestParam(value = "fileNums", required = false) List<String> fileNumList) throws Exception {

        boardService.insertBoard(bdv);

        if(fileNumList != null) {
            int boardNum = bdv.getBoardNum();
            fileService.insertBoardNum(fileNumList, boardNum);
        }
        return bdv;
    }

    // 게시글 수정 처리
    @RequestMapping(value = "/board", method = RequestMethod.PUT)
    public BoardVo updateBoard(BoardVo bdv, @RequestParam(value = "boardNum") int boardNum, @RequestParam(value = "deleteFileList[]", required = false) ArrayList<String> deleteFileList, @RequestParam(value = "fileNums", required = false) List<String> fileNumList) throws Exception {

        System.out.println(deleteFileList);
        System.out.println(fileNumList);

        boardService.updateBoard(bdv);

        if (fileNumList != null){
            fileService.insertBoardNum(fileNumList, boardNum);
        }

        if (deleteFileList != null) {
            fileService.deleteExstingFile(deleteFileList, boardNum);
        }
        return bdv;
    }

    // 게시글 상세 목록
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public BoardVo getOneBoard(@RequestParam(value="boardNum")int boardNum) throws Exception {
        return boardService.getBoardDetail(boardNum);
    }

    // 게시글 삭제 처리
    @RequestMapping(value = "/board", method = RequestMethod.DELETE)
    public int deleteBoard(int boardNum) throws Exception  {
        return boardService.deleteBoard(boardNum);
    }
}
