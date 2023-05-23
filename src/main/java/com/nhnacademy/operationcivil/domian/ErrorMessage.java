package com.nhnacademy.operationcivil.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ErrorMessage {
    @Getter
    String statusCode;
    @Getter
    String timestamp;
    @Getter
    String message;

}
