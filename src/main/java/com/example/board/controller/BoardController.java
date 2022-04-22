package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import com.example.board.vo.BoardVo;
import com.example.board.vo.Criteria;
import com.example.board.vo.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    // 게시글 전체 리스트 페이지
    @RequestMapping(value = {"/boardListPage", "/"}, method = RequestMethod.GET)
    public String openBoardList(Criteria criteria, Model model) throws Exception {

        int boardtotalCount = boardService.totalRecordCount();
        Paging paging = new Paging(criteria, boardtotalCount);

        model.addAttribute("paging", paging);
        model.addAttribute("list", boardService.selectBoardList(criteria));

        return "board/bList";
    }

    // 게시글 전체 리스트 페이지: 체크박스 선택 삭제
    @ResponseBody
    @RequestMapping(value = "/deleteBoardList", method = RequestMethod.POST)
    public String deleteBoardList(@RequestBody List<String> boardCheckList)throws Exception {

        for (int i = 0; i < boardCheckList.size(); i++) {
           boardService.deleteBoardList(Integer.parseInt(boardCheckList.get(i)));
        }

        return "redirect:/boardListPage";
    }

    // 게시글 전체 리스트 페이지: jQuery 페이징
    @RequestMapping(value = "boardListTable", method = RequestMethod.GET)
    public String boardListTable() throws Exception {
        return "board/Datatables";
    }

    // 게시글 작성 페이지
    @RequestMapping(value = "/boardWritePage", method = RequestMethod.GET)
    public String addBoardPage(HttpServletRequest request, Model model) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        model.addAttribute("userId", userId);

        return "board/bWrite";
    }

    // 게시글 상세 페이지: 게시글 수정 및 삭제 + 댓글 리스트 수정 및 삭제
    @RequestMapping(value = "/{boardNum}", method = RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardNum") int boardNum, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        ModelAndView mv = new ModelAndView("board/bDetail");

        BoardVo board = boardService.getBoardDetail(boardNum);

        // 게시판 상세 내용
        mv.addObject("board", board);
        // 게시판 댓글 리스트
        mv.addObject("comment", commentService.commentList(boardNum));
        // 로그인중인 아이디 가져오기
        mv.addObject("userId", userId);

        return mv;
    }

    @GetMapping("/downloadFile")
    public void donwloadFile(ModelMap modelMap, HttpServletResponse response) throws UnsupportedEncodingException {
        // board Id 를 우선 변수로 받음
        // BOARD 테이블에서 관련 boardId를 바탕으로 파일의 이름 (=즉 upload할때 등록했던 경로+ 파일 이름을 가지고옴)
        File file = new File("C:\\file_repo/"+"99CBFB3C5AE2934205.jpg");
        // modelMap.put("file", file);

        // %20 = URL escape code 중 띄어쓰기 즉, 스페이스를 의미
        // 정규표현식에서 +는 "앞 문자가 1개 이상 존재할 수 있다"라는 뜻으로 특수문자인 +를 표현하기 위해서는 \\+로 표현한다.
        String fileName = URLEncoder.encode(file.getName(), String.valueOf(StandardCharsets.UTF_8)).replaceAll("\\+", "%20");
        // HTTP Response Body에 오는 컨텐츠의 특징을 알려주는 속성이다.
        // Default 값은 inline으로 web에 전달되는 data라고 생각하면 좋다.
        // 특수한 경우는 Content-Disposition에 attachment를 주는 경우로, 이때 파일명과 함께 주게되면 Body에 오는 값을 다운로드 받으라는 뜻이 된다.
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;; filename=\"%s\"", fileName));
        response.setContentLength((int) file.length()); // 유효성 검사

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public void uploadFile( @RequestBody MultipartFile uploadFile, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

//        if (!boardNum.equals("null")) {
//            Long boardNum2 = Long.parseLong(boardNum);
//            boardService.fileUpload(boardNum2, uploadFile, userId);
//        } else {
//            boardService.fileUpload(null, uploadFile, userId);
//        }

        boardService.fileUpload(uploadFile, userId);


    }

//    @Component("file-download-view")
//    public static class FileDownloadView extends AbstactView {
//
//    }

}
