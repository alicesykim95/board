package com.example.board.dto;

import lombok.Data;

@Data
public class BoardInsertDto {

    private int boardNum;
    private String writer;
    private String title;
    private String content;
    private String fileNums;

}


