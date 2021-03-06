package com.pt.bloglib.Exception;

import com.pt.bloglib.dto.ExceptionMsg;
import com.pt.bloglib.dto.Result;
import com.pt.bloglib.enums.RequestCodeEnum;
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
     * 注册验证码错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UsernameOrVerifyCodeException.class)
    public Result UsernameOrVerifyCodeExceptionHandler(Exception e) {
        e.printStackTrace();
        return new Result(RequestCodeEnum.ERROR.getState(), e.getMessage(), e);
    }

    /**
     * 用户名已存在
     */
    @ExceptionHandler(UserExistsException.class)
    public Result UserExistsExceptionHandler(Exception e) {
        e.printStackTrace();
        return new Result(RequestCodeEnum.ERROR.getState(), e.getMessage(), e);
    }

    /**
     * 该用户名不存在
     */
    @ExceptionHandler(UserNoFoundException.class)
    public Result UserNoFoundExceptionHandler(Exception e) {
        e.printStackTrace();
        return new Result(RequestCodeEnum.ERROR.getState(), e.getMessage(), e);
    }

    /**
     * 更新密码失败
     */
    @ExceptionHandler(ChangePasswordException.class)
    public Result ChangePasswordExceptionHandler(Exception e) {
        e.printStackTrace();
        return new Result(RequestCodeEnum.ERROR.getState(), e.getMessage(), e);
    }

    /**
     * String转int
     */
    @ExceptionHandler(NumberFormatException.class)
    public Result NumberFormatExceptionHandler(Exception e) {
        e.printStackTrace();
        return new Result(RequestCodeEnum.ERROR.getState(),"文章id格式错误", e);
    }

    /**
     * 保存评论失败
     */
    @ExceptionHandler(DiscussSaveException.class)
    public Result DiscussSaveExceptionHandler(Exception e) {
        e.printStackTrace();
        return new Result(RequestCodeEnum.ERROR.getState(),"保存评论失败", e);
    }


    /**
     * 保存回复失败
     */
    @ExceptionHandler(ReplySaveException.class)
    public Result ReplySaveExceptionHandler(Exception e) {
        e.printStackTrace();
        return new Result(RequestCodeEnum.ERROR.getState(),"保存回复失败", e);
    }



    /**
     * String转int
     */
    @ExceptionHandler(NoSuchUserInfoException.class)
    public Result NoSuchUserInfoExceptionHandler(Exception e) {
        e.printStackTrace();
        return new Result(RequestCodeEnum.ERROR.getState(),"token错误或已过时", e);
    }

    /**
     * 404 没找到资源
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ExceptionMsg noHandlerFoundExceptionHandler(Exception e) {
        e.printStackTrace();
        return ExceptionMsg.createExceptionMsg(RequestStatusEnum.NOTFOUND.getState(), RequestStatusEnum.NOTFOUND.getMessage());
    }

    /**
     * 其他方式
     */
    @ExceptionHandler(Exception.class)
    public ExceptionMsg exceptionHandler(Exception e) {
        e.printStackTrace();
        return ExceptionMsg.createExceptionMsg(RequestStatusEnum.SERVICEERROR.getState(), RequestStatusEnum.SERVICEERROR.getMessage());
    }
}
