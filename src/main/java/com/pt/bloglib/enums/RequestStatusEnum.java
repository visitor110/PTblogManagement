package com.pt.bloglib.enums;

public enum RequestStatusEnum {

    OK(200, "成功"),

    ERROR(201, "失败"),

    LOGINERROR(202, "用户名或密码错误"),

    TOKENEXPIREE(203, "token过期"),

    ACCESSERROR(403, "权限不足"),

    REMOTEERROR(204, "远程调用失败"),

    REPERROR(205, "重复操作"),

    SERVICEERROR(500, "业务层错误"),

    NOTFOUND(404, "资源不存在");

    private int state;
    private String message;

    private RequestStatusEnum(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public static RequestStatusEnum stateOf(int state) {
        for (RequestStatusEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }
}
