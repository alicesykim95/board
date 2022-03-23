package com.example.board.service;

import com.example.board.mapper.UserMapper;
import com.example.board.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 회원가입
    public int insertMember(UserVo uv) throws Exception{
        return userMapper.insertMember(uv);
    }

    // 로그인
    public UserVo loginMember(UserVo uv) throws Exception{

        UserVo userVo =  userMapper.loginMember(uv);

        if (userVo == null) {
            return null;
        }

        return userVo;
    }

}
