package com.example.board.controller;

import com.example.board.service.FileService;
import com.example.board.vo.FileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    // 파일 다운로드
    @GetMapping("/downloadFile/{fileNum}")
    @ResponseBody
    public void donwloadFile(HttpServletResponse response, @PathVariable int fileNum) throws Exception {

        FileVo fv = (FileVo) fileService.fileDownload(fileNum);
        String filePath = fv.getFilePath();
        String savedName = fv.getFileSavedName();

        File file = new File(filePath + savedName);

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        String fileName = URLEncoder.encode(file.getName(), String.valueOf(StandardCharsets.UTF_8)).replaceAll("\\+", "%20");
        response.setContentType(mimeType);
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;; filename=\"%s\"", fileName));
        response.addHeader("X-Content-Type-Options", "nosniff");
        response.setContentLength((int) file.length()); // 유효성 검사

        try (

                InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
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
    public Map<String, Object> uploadFile(@RequestParam(value = "fileList", required = false)List<MultipartFile> uploadFile, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        return fileService.insertFile(uploadFile, userId);

    }


}
