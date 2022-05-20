package com.example.board.service;

import com.example.board.mapper.LikeDislikeMapper;
import com.example.board.vo.LikeDislikeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeDislikeService {
    private final LikeDislikeMapper likeDislikeMapper;

    // 좋아요 클릭시 테이블에 정보 입력 및 추천수 중복 방지
    public int likeInfoUpdate(LikeDislikeVo ldv)throws Exception{

        int likeTotalCount = likeDislikeMapper.likeCount(ldv);

        if (likeTotalCount == 0){
            likeDislikeMapper.likeSave(ldv);
            likeDislikeMapper.likeUpdate(ldv);
        } else if (likeTotalCount == 1){
            likeDislikeMapper.likeDelete(ldv);
        }
        return likeTotalCount;
    }
}
