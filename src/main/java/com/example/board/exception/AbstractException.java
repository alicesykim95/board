package com.example.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractException extends RuntimeException {

    private ErrorCode errorCode;
    private String[] errorDetails;
    private String message;
    private HttpStatus status;


    public static AbstractException from(ErrorCode errorCode, String[] errorDetails, String message, HttpStatus status) {
        return new AbstractException(errorCode, errorDetails, message, status);
    }
}
