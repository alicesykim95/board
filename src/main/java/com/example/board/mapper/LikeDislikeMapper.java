package com.example.board.mapper;

import com.example.board.vo.LikeDislikeVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeDislikeMapper {

        // 좋아요 클릭시 테이블에 정보 입력
        void likeSave(LikeDislikeVo ldv) throws Exception;

        // 좋아요 추천수 추가
        int likeUpdate(LikeDislikeVo ldv) throws Exception;

        // 좋아요 추천수 삭제
        void likeDelete(LikeDislikeVo ldv) throws Exception;

        // 추천수 가져오기
        int likeCount(LikeDislikeVo ldv) throws Exception;

}
