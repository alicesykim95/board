package com.example.board.mapper;

import com.example.board.vo.FileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    // 파일 업로드
    void insertFile(FileVo fv)throws Exception;

    // 보드 넘버 삽입
    void insertBoardNum(FileVo fv)throws Exception;

    // 파일 리스트 조회
    List<FileVo> selectFile(int boardNum)throws Exception;

    // 파일 다운로드
    List<FileVo> fileDownload(int fileNum)throws Exception;
}
