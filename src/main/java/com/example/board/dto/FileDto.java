package com.example.board.dto;

import lombok.Data;

@Data
public class FileDto {
    private String fileOriginalName;
    private String fileSavedName;
    private String filePath;
    private String fileCreatedUser;

    public FileDto(String fileOriginalName, String fileSavedName, String filePath, String fileCreatedUser) {
        this.fileOriginalName = fileOriginalName;
        this.fileSavedName = fileSavedName;
        this.filePath = filePath;
        this.fileCreatedUser = fileCreatedUser;
    }
}
