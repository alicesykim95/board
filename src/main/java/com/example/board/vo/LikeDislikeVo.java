package com.example.board.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LikeDislikeVo {
    private int likeNumber;
    private int like;
    private int dislike;
    private int boardNum;
    private String userId;
    private Date inputTime;
}
