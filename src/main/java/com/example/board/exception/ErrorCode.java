package com.example.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // 400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER("파라미터 값을 확인해주세요."),

    // 404 NOT_FOUND
    USER_NOT_FOUND("유저를 찾지 못하였습니다."),

    DATA_PROCESSING_ERROR("DATA_PROCESSING_ERROR"),
    REQUIRED_PARAMETER("REQUIRED_PARAMETER"),
    BAD_REQUEST_PARAMETER("BAD_REQUEST_PARAMET");

    private final String code;
}
