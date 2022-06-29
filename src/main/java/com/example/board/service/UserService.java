package com.example.board.service;

import com.example.board.exception.AbstractException;
import com.example.board.mapper.UserMapper;
import com.example.board.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.board.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    // 회원가입
    public int insertMember(UserVo uv) throws Exception {
        return userMapper.insertMember(uv);
    }

    // 로그인
    public UserVo loginMember(UserVo uv)throws AbstractException{

        UserVo userVo = userMapper.loginMember(uv);

        if (userVo != null) {
            return userVo;
        } else {
            throw new AbstractException(USER_NOT_FOUND);
        }
    }

}
