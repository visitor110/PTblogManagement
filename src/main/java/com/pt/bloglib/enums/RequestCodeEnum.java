package com.pt.bloglib.enums;

public enum RequestCodeEnum {

    OK(200),

    ERROR(201),

    LOGINERROR(202),

    TOKENEXPIREE(203),

    ACCESSERROR(403),

    REMOTEERROR(204),

    REPERROR(205),

    SERVICEERROR(500),

    NOTFOUND(404);

    private Integer state;

    private RequestCodeEnum(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

}
