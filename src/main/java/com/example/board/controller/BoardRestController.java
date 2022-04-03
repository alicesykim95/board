package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.service.UserService;
import com.example.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    // 게시글 전체 페이지 목록
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public List<BoardVo> getListBoard() throws Exception {
        return boardService.selectBoardListNone();
    }

    // 게시글 작성 처리
    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public int addBoard(BoardVo bdv, MultipartHttpServletRequest request) throws Exception {

        MultipartFile file = request.getFile("uploadFile");
        assert file != null;
        System.out.println(file);
        file.transferTo(new File("C:\\file_repo/"+file.getOriginalFilename()));

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
        return boardService.deleteBoard(boardNum);
    }


}
