package com.pt.bloglib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel("响应实体")

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ToString
public class ExceptionMsg {

    @ApiModelProperty(value = "返回码", dataType = "Integer")
    private int code;

    @ApiModelProperty(value = "返回信息", dataType = "String")
    private String message;

    @ApiModelProperty(value = "返回数据", dataType = "Object")
    private Object data;

    private ExceptionMsg() {
    }

    private ExceptionMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private ExceptionMsg(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ExceptionMsg createExceptionMsg(int code, String message) {
        return new ExceptionMsg(code, message);
    }

    public static ExceptionMsg createExceptionMsg(int code, String message, Object data) {
        return new ExceptionMsg(code, message, data);
    }
}