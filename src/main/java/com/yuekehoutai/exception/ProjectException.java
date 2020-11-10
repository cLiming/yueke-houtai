package com.yuekehoutai.exception;

import lombok.Data;

@Data
public class ProjectException extends RuntimeException {
    private Integer code;
    public ProjectException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
