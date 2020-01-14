package com.pt.bloglib.dto;

import lombok.Data;

/**
 * 封装后端数据传前端
 */
@Data
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Result(){}

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
