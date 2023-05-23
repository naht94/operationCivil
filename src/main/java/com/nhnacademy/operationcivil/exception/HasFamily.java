package com.nhnacademy.operationcivil.exception;

public class HasFamily extends RuntimeException{
    public HasFamily() {
        super("남은 가족이 있어 삭제가 불가능합니다");
    }
}
