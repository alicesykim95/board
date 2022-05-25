package com.example.board.mapper;

import com.example.board.vo.LikeDislikeVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeDislikeMapper {

        // 좋아요 및 싫어요 클릭시 테이블에 정보 입력
        void likeDislikeSave(LikeDislikeVo ldv) throws Exception;

        // 좋아요 추가
        void likeUpdate(LikeDislikeVo ldv) throws Exception;

        // 좋아요 삭제
        void likeDelete(LikeDislikeVo ldv) throws Exception;

        // likeCheck: 좋아요 체크
        int likeCount(LikeDislikeVo ldv) throws Exception;

        // likeTotalCheck: 좋아요 전체 가져오기
        int likeTotalCount(LikeDislikeVo ldv) throws Exception;

        // 싫어요 추가
        void dislikeUpdate(LikeDislikeVo ldv) throws Exception;

        // 싫어요 삭제
        void dislikeDelete(LikeDislikeVo ldv) throws Exception;

        // dislikeCheck: 싫어요 체크
        int dislikeCount(LikeDislikeVo ldv) throws Exception;

        // dislikeTotalCheck: 싫어요 전체 가져오기
        int dislikeTotalCount(LikeDislikeVo ldv) throws Exception;
}
