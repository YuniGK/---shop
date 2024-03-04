package com.test.shop.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //예외처리
    @ExceptionHandler(value = RuntimeException.class)
    public Response<Void> handleRuntimeException(RuntimeException e){
        return Response.fail(e.getMessage());
    }
}
