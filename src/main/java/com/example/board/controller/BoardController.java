package com.example.board.controller;

import com.example.board.dto.LikeDislikeDto;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import com.example.board.service.FileService;
import com.example.board.service.LikeDislikeService;
import com.example.board.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final FileService fileService;
    private final LikeDislikeService likeDislikeService;

    // 게시글 전체 리스트 페이지
    @RequestMapping(value = {"/boardListPage", "/"}, method = RequestMethod.GET)
    public String openBoardList(Criteria criteria, Model model) throws Exception {

        int boardtotalCount = boardService.totalRecordCount(criteria);
        Paging paging = new Paging(criteria, boardtotalCount);


        model.addAttribute("paging", paging);
        model.addAttribute("list", boardService.selectBoardList(criteria));
        model.addAttribute("keyword", criteria.getKeyword());

        return "board/bList";
    }


    // 게시글 전체 리스트 페이지: 체크박스 선택 삭제
    @ResponseBody
    @RequestMapping(value = "/deleteBoardList", method = RequestMethod.POST)
    public String deleteBoardList(@RequestBody List<String> boardCheckList)throws Exception {

        for (String boardCheck : boardCheckList) {
            boardService.deleteBoardList(Integer.parseInt(boardCheck));
        }

        return "redirect:/boardListPage";
    }

    // 게시글 전체 리스트 페이지: jQuery 페이징
    @RequestMapping(value = "/boardListTable", method = RequestMethod.GET)
    public String boardListTable() {
        return "board/Datatables";
    }

    // 게시글 작성 페이지
    @RequestMapping(value = "/boardWritePage", method = RequestMethod.GET)
    public String addBoardPage(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("userId", userId);

        return "board/bWrite";
    }

    // 게시글 상세 페이지: 게시글 수정 및 삭제 + 댓글 리스트 수정 및 삭제
    @RequestMapping(value = "/{boardNum}", method = RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardNum") int boardNum, HttpServletRequest request, LikeDislikeVo ldv, LikeDislikeDto ldd) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        ModelAndView mv = new ModelAndView("board/bDetail");

        BoardVo board = boardService.getBoardDetail(boardNum);

        List<FileVo> fv = fileService.selectFile(boardNum);

        ldv.setUserId(userId);
        ldv.setBoardNum(boardNum);

        ldd.setLikeCheck(likeDislikeService.likeCount(ldv));
        ldd.setLikeCnt(likeDislikeService.likeTotalCount(ldv));
        ldd.setDislikeCheck(likeDislikeService.dislikeCount(ldv));
        ldd.setDislikeCnt(likeDislikeService.dislikeTotalCount(ldv));

        // 게시판 상세 내용
        mv.addObject("board", board);
        // 게시판 댓글 리스트
        mv.addObject("comment", commentService.commentList(boardNum));
        // 로그인중인 아이디 가져오기
        mv.addObject("userId", userId);
        // 파일 조회 내용
        mv.addObject("files", fv);
        // 좋아요 싫어요
        mv.addObject("likeDislike", ldd);

        return mv;
    }


}
