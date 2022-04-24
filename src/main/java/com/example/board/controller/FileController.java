package com.example.board.controller;

import com.example.board.service.FileService;
import com.example.board.vo.FileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    // 파일 다운로드
    @GetMapping("/downloadFile")
    @ResponseBody
    public void donwloadFile(ModelMap modelMap, HttpServletResponse response, @RequestBody int fileNum) throws Exception {

        FileVo fv = (FileVo) fileService.fileDownload(fileNum);
        String filePath = fv.getFilePath();
        String savedName = fv.getFileSavedName();

        // board Id 를 우선 변수로 받음
        // BOARD 테이블에서 관련 boardId를 바탕으로 파일의 이름 (=즉 upload할때 등록했던 경로+ 파일 이름을 가지고옴)
        File file = new File(filePath + savedName);

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

    // 파일 업로드
    @PostMapping("/uploadFile")
    @ResponseBody
    public int uploadFile(@RequestBody MultipartFile uploadFile, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

//        if (!boardNum.equals("null")) {
//            Long boardNum2 = Long.parseLong(boardNum);
//            boardService.fileUpload(boardNum2, uploadFile, userId);
//        } else {
//            boardService.fileUpload(null, uploadFile, userId);
//        }

        return fileService.insertFile(uploadFile, userId);

    }

}
