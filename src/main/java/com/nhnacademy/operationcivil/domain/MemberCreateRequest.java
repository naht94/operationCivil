package com.nhnacademy.operationcivil.domain;

import lombok.Data;

@Data
public class MemberCreateRequest {
    private String id;
    private String name;
    private String pwd;
    private String authority;
}
