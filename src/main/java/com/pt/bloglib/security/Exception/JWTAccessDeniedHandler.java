package com.pt.bloglib.security.Exception;

import com.pt.bloglib.dto.Result;
import com.pt.bloglib.enums.RequestCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证过的用户访问无权限资源时的异常
 */
public class JWTAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 当用户尝试访问需要权限才能的REST资源而权限不足的时候，
     * 将调用此方法发送401响应以及错误信息
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        accessDeniedException = new AccessDeniedException("Sorry you don not enough permissions to access it!");
//        response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
        Result res = new Result(RequestCodeEnum.ACCESSERROR.getState(),"抱歉你没有权限",null);
        //返回json形式的错误信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(res);
      //  response.getWriter().println("抱歉你没有权限");
        response.getWriter().flush();

    }
}
