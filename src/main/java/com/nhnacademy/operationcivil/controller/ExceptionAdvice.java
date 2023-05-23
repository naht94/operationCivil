package com.nhnacademy.operationcivil.controller;

import com.nhnacademy.operationcivil.domian.ErrorMessage;
import com.nhnacademy.operationcivil.exception.NotEnoughPathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = NotEnoughPathParam.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage notEnoughPathParam(NotEnoughPathParam ex) {
        return new ErrorMessage(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                LocalDateTime.now().toString().substring(0, 19),
                ex.getMessage());
    }
}
