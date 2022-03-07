package com.example.board.service;

import com.example.board.mapper.MemberMapper;
import com.example.board.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public int insertMember(MemberVo mbv) throws Exception{
        return memberMapper.insertMember(mbv);
    }

    public MemberVo loginMember(MemberVo mbv) throws Exception{

        MemberVo memberVo =  memberMapper.loginMember(mbv);

        if (memberVo == null) {
            return null;
        }
        return memberVo;
    }


}
