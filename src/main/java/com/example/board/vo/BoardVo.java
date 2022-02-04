package com.example.board.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVo {
    private int boardNum;
    private String writer;
    private String title;
    private String content;
    private Date createdTime;
    private int hitCnt;
}
