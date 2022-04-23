package com.example.board.service;

import com.example.board.mapper.FileMapper;
import com.example.board.vo.FileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    // 파일 업로드
    public int insertFile(MultipartFile file, String userId) throws Exception {

        FileVo fv = new FileVo();

        try {
            // assert: 확성을 확인하는 용도로 file이 null이 아니면 지나가고, 반대면 AssertionError예외가 발생한다.
            assert file != null;

            String originName = file.getOriginalFilename();
            String filePath = "C:\\file_repo/";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss").withZone(ZoneOffset.UTC);
            String savedName = formatter + "_" + originName;
            file.transferTo(new File(filePath + savedName));

            //FileDto fd = new FileDto(originName, savedName, filePath, userId);
            fv.setFileOriginalName(originName);
            fv.setFilePath(filePath);
            fv.setFileSavedName(savedName);
            fv.setFileCreatedUser(userId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return fileMapper.insertFile(fv);
    }

}
