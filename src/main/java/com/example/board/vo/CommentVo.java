package com.example.board.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {

    private int commentNum;
    private int boardNum;
    private String commentContent;
    private String commentWriter;
    private Date commentRegTime;

}
