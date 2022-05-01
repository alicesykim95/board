package com.example.board.dto;

import lombok.Data;

@Data
public class LikeDislikeDto {
    private int like;
    private int boardNum;
    private String userId;
    private String likeDisLike;
}
