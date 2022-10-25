package com.example.board.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {

    private int commentNum;
    private int boardNum;
    private String commentContent;
    private String commentWriter;
    private Date commentRegTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Soul")
    private Date commentModiTime;
}
