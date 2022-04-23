package com.example.board.mapper;

import com.example.board.vo.FileVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    // 파일 업로드
    int insertFile(FileVo fv)throws Exception;
}
