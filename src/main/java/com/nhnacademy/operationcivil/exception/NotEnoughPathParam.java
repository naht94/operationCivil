package com.nhnacademy.operationcivil.exception;

public class NotEnoughPathParam extends RuntimeException {
    public NotEnoughPathParam(String target) {
        super("not enough " + target);
    }
}
