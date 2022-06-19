package com.example.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {


    DATA_PROCESSING_ERROR("DATA_PROCESSING_ERROR"),
    REQUIRED_PARAMETER("REQUIRED_PARAMETER");

    private String code;
}
