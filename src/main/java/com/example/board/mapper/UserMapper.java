package com.example.board.mapper;

import com.example.board.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 회원가입
    int insertMember(UserVo uv) throws Exception;

    // 로그인
    UserVo loginMember(UserVo uv) throws Exception;
}
