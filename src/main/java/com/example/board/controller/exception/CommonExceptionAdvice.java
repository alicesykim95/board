package com.example.board.controller.exception;

import com.example.board.exception.AbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.board.exception.ErrorCode.INVALID_PARAMETER;
import static com.example.board.exception.ErrorCode.USER_NOT_FOUND;


@ControllerAdvice(basePackages={"com.example.board.controller.UserLoginController"})
public class CommonExceptionAdvice {

    @ExceptionHandler(AbstractException.class)
    private ResponseEntity<Object> BadRequestException (AbstractException e) {
        return ResponseEntity.status(400).body(AbstractException.from(INVALID_PARAMETER,"파라미터 값을 확인해주세요", e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(AbstractException.class)
    private ResponseEntity<Object> userNameNotFound(AbstractException e) {
        return ResponseEntity.status(404).body(AbstractException.from(USER_NOT_FOUND,"유저를 찾지 못하였습니다.", e.getMessage(), HttpStatus.NOT_FOUND));
    }
}
