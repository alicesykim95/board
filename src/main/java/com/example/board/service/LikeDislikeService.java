package com.example.board.service;

import com.example.board.mapper.LikeDislikeMapper;
import com.example.board.vo.LikeDislikeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeDislikeService {
    private final LikeDislikeMapper likeDislikeMapper;

    // 좋아요 클릭시 테이블에 정보 입력
    public void likeSave(LikeDislikeVo ldv)throws Exception {
        likeDislikeMapper.likeSave(ldv);
    }

    // 좋아요 추천수 추가
    public void likeUpdate(LikeDislikeVo ldv)throws Exception {
        likeDislikeMapper.likeUpdate(ldv);
    }

    // 좋아요 추천수 삭제
    public void likeDelete(LikeDislikeVo ldv)throws Exception {
        likeDislikeMapper.likeDelete(ldv);
    }

    // likeCheck: 추천수 가져오기
    public int likeCount(LikeDislikeVo ldv)throws Exception {
        return likeDislikeMapper.likeCount(ldv);
    }

    // likeTotalCheck: 추천수 가져오기
    public int likeTotalCount(LikeDislikeVo ldv)throws Exception {
        return likeDislikeMapper.likeTotalCount(ldv);
    }


}
