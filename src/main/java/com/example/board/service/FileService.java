package com.example.board.service;

import com.example.board.mapper.FileMapper;
import com.example.board.vo.FileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    // 파일 업로드
    public Map<String, Object> insertFile(List<MultipartFile> multipartFile, String userId) throws Exception {

        // assert: 확성을 확인하는 용도로 file이 null이 아니면 지나가고, 반대면 AssertionError예외가 발생한다.
        // assert file != null;

        Map<String, Object> result = new HashMap<String, Object>();

        List<String> fileNums = new ArrayList<String>();

        result.put("result", "ERROR");
        try {
            if (multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {

                for (MultipartFile file : multipartFile) {
                    String originName = file.getOriginalFilename();
                    String filePath = "C:\\file_repo/";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss").withZone(ZoneOffset.UTC);
                    String savedName = formatter + "_" + originName;
                    file.transferTo(new File(filePath + savedName));

                    result.put("fileOriginalName", originName);
                    result.put("filePath", filePath);
                    result.put("fileSavedName", savedName);
                    result.put("fileCreatedUser", userId);

                    fileMapper.insertFile(result);

                    fileNums.add(String.valueOf(result.get("fileNum")));

                    result.put("fileNums", fileNums.toString());
                    result.put("result", "SUCCESS");
                }
            } else {
                result.put("result", "SUCCESS");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.put("result", "ERROR");
        }

        return result;

        // FileDto fd = new FileDto(originName, savedName, filePath, userId);
        // FileVo fv = new FileVo();
        // fv.setFileOriginalName(originName);
        // fv.setFilePath(filePath);
        // fv.setFileSavedName(savedName);
        // fv.setFileCreatedUser(userId);
        // int fileNum = fv.getFileNum();
        // System.out.println(fileNum);
        // return fileNum;
    }

    // 파일 보드넘버 삽입
    public void insertBoardNum(FileVo fv)throws Exception{
        fileMapper.insertBoardNum(fv);
    }

    // 파일 조회
    public List<FileVo> selectFile(int boardNum)throws Exception{
        return fileMapper.selectFile(boardNum);
    }

    // 파일 다운로드
    public List<FileVo> fileDownload(int fileNum)throws Exception {
        return fileMapper.fileDownload(fileNum);
    }
}
