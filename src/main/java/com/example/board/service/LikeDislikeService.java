package com.example.board.service;

import com.example.board.mapper.LikeDislikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeDislikeService {

    private final LikeDislikeMapper likeDislikeMapper;
//
//    // 좋아요 싫어요
//    public void likeDislike(LikeDislikeDto ldd) throws Exception {
//
//
//        if (ldd.getLike() == 1){
//            ldd.setLikeDisLike("LIKE");
//        } else if(ldd.getLike() == -1) {
//            ldd.setLikeDisLike("DISLIKE");
//        }
//
//        String num = likeDislikeMapper.selectLikeInfo(ldd);
//
//        if (num == null) {
//            likeDislikeMapper.like(ldd);
//        } else {
//            if (num.equals(ldd.getLikeDisLike())) {
//                // 삭제 로직
//            } else {
//                // 좋아요가 있는데 싫어요오를 누른 경우
//            }
//        }



//    }

}
