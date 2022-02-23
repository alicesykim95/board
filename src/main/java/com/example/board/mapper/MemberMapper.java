package com.example.board.mapper;

import com.example.board.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int insertMember(MemberVo mbv) throws Exception;

}
