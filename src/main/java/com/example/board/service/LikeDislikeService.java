package com.example.board.service;

import com.example.board.mapper.LikeDislikeMapper;
import com.example.board.vo.LikeDislikeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeDislikeService {
    private final LikeDislikeMapper likeDislikeMapper;

    // 좋아요 및 싫어요 처음 클릭시 테이블에 정보 입력
    public void likeDislikeSave(LikeDislikeVo ldv)throws Exception {
        likeDislikeMapper.likeDislikeSave(ldv);
    }

    // 좋아요 추가
    public void likeUpdate(LikeDislikeVo ldv)throws Exception {
        likeDislikeMapper.likeUpdate(ldv);
    }

    // 좋아요 삭제
    public void likeDelete(LikeDislikeVo ldv)throws Exception {
        likeDislikeMapper.likeDelete(ldv);
    }

    // likeCheck: 좋아요 체크
    public int likeCount(LikeDislikeVo ldv)throws Exception {
        return likeDislikeMapper.likeCount(ldv);
    }

    // likeTotalCheck: 좋아요 전체 가져오기
    public int likeTotalCount(LikeDislikeVo ldv)throws Exception {
        return likeDislikeMapper.likeTotalCount(ldv);
    }

    // 싫어요 추가
    public void dislikeUpdate(LikeDislikeVo ldv)throws Exception {
        likeDislikeMapper.dislikeUpdate(ldv);
    }

    // 싫어요 삭제
    public void dislikeDelete(LikeDislikeVo ldv)throws Exception {
        likeDislikeMapper.dislikeDelete(ldv);
    }

    // dislikeCheck: 싫어요 체크
    public int dislikeCount(LikeDislikeVo ldv)throws Exception {
        return likeDislikeMapper.dislikeCount(ldv);
    }

    // dislikeTotalCheck: 싫어요 전체 가져오기
    public int dislikeTotalCount(LikeDislikeVo ldv)throws Exception {
        return likeDislikeMapper.dislikeTotalCount(ldv);
    }




}
