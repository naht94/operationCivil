package com.nhnacademy.operationcivil.exception;

public class HasNoTarget extends RuntimeException{
    public HasNoTarget(Object message) {
        super(message.toString());
    }
}
