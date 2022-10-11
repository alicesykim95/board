package com.example.board.service;

import com.example.board.mapper.UserMapper;
import com.example.board.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    // 회원가입
    public int insertMember(UserVo uv) throws Exception {
        return userMapper.insertMember(uv);
    }

    // 로그인
    public UserVo loginMember(UserVo uv)throws Exception{

        UserVo userVo = userMapper.loginMember(uv);

        return userVo;
    }

}
