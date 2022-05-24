package com.example.board.dto;

import lombok.Data;

@Data
public class LikeDislikeDto {
    private int likeBtn;
    private int dislikeBtn;
    private int boardNum;
    private String userId;
    private String likeDisLike;
}
