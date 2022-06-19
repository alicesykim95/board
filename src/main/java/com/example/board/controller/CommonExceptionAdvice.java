package com.example.board.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice(basePackages={"com.example.board.controller.BoardController"})
public class CommonExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public String except() {
        return "error";
    }

//    @ExceptionHandler(AbstractException.class)
//    public ResponseEntity<Object> exception(Exception e) {
//        return ResponseEntity.status(500).body(AbstractException.from(500, "이래서 에러 발생", e.getMessage(), 500));
//    }
}
