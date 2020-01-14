package com.pt.bloglib.Exception;

import com.pt.bloglib.dto.ExceptionMsg;
import com.pt.bloglib.enums.RequestStatusEnum;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求方式错误
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionMsg httpRequestMethodNotSupportedExceptionHandler(Exception e) {
        e.printStackTrace();
        return ExceptionMsg.createExceptionMsg(RequestStatusEnum.ERROR.getState(), RequestStatusEnum.ERROR.getMessage());
    }

    /**
     * 404 没找到资源
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ExceptionMsg noHandlerFoundExceptionHander(Exception e) {
        e.printStackTrace();
        return ExceptionMsg.createExceptionMsg(RequestStatusEnum.NOTFOUND.getState(), RequestStatusEnum.NOTFOUND.getMessage());
    }

    /**
     * 其他方式
     */
    @ExceptionHandler(Exception.class)
    public ExceptionMsg exceptionHander(Exception e) {
        e.printStackTrace();
        return ExceptionMsg.createExceptionMsg(RequestStatusEnum.SERVICEERROR.getState(), RequestStatusEnum.SERVICEERROR.getMessage());
    }
}
