package com.example.board.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {

    private int comment_num;
    private int board_num;
    private String comment_content;
    private String user_id;
    private Date comment_reg_time;

}
