package com.example.board.service;

import com.example.board.mapper.MemberMapper;
import com.example.board.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public int insertMember(MemberVo mbv) throws Exception{
        return memberMapper.insertMember(mbv);
    }

}
