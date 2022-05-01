package com.example.board.mapper;

import com.example.board.vo.FileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileMapper {

    // 파일 업로드
    void insertFile(Map<String, Object> file)throws Exception;

    // 보드 넘버 삽입
    void insertBoardNum(Map<String, Object> params)throws Exception;

    // 파일 리스트 조회
    List<FileVo> selectFile(int boardNum)throws Exception;

    // 파일 다운로드
    FileVo fileDownload(int fileNum)throws Exception;
}
