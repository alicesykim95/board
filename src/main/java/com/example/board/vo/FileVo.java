package com.example.board.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FileVo {
    private int fileNum;
    private String fileOriginalName;
    private String fileSavedName;
    private String filePath;
    private Timestamp fileCreatedTime;
    private String fileCreatedUser;
    private int fileDelete;
    private int boardNum;
}
