package com.pt.bloglib.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pt.bloglib.dao.pojo.LoginUserPojo;
import com.pt.bloglib.dao.pojo.UserInfoPojo;
import com.pt.bloglib.dto.Result;
import com.pt.bloglib.enums.RequestCodeEnum;
import com.pt.bloglib.security.entity.JwtUser;
import com.pt.bloglib.security.utils.JwtUtil;
import com.pt.bloglib.utils.RedisUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法 ,
 * attemptAuthentication：接收并解析用户凭证。
 * successfulAuthentication：用户成功登录后，这个方法会被调用，我们在这个方法里生成token并返回。
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();

    private AuthenticationManager authenticationManager;

    private RedisUtil redisUtil;

    private UserInfoPojo userInfoPojo;

    private LoginUserPojo loginUserPojo;

    public JWTLoginFilter(AuthenticationManager authenticationManager,
                          UserInfoPojo userInfoPojo,
                          RedisUtil redisUtil) {
        this.authenticationManager = authenticationManager;
        this.userInfoPojo = userInfoPojo;
        this.redisUtil = redisUtil;
        // 设置URL，以确定是否需要身份验证
        super.setFilterProcessesUrl("/user/login");
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 从输入流中获取到登录的信息
            loginUserPojo = objectMapper.readValue(request.getInputStream(), LoginUserPojo.class);
            rememberMe.set(loginUserPojo.getRemember());
            System.out.println("loginUserPojo\t" + loginUserPojo);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    loginUserPojo.getUsername(), loginUserPojo.getPassword());
            return authenticationManager.authenticate(authRequest);
        } catch (Exception e) {
            logger.error("request 中提取 LoginUserPojo 失败\t", e);
            e.printStackTrace();
            Result result = new Result(RequestCodeEnum.ERROR.getState(), "用户不存在或密码错误", e);
            try {
                response = addResultToResponse(response, result);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 如果验证成功，就生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException {

        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        List<String> roles = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // 创建 Token
        String token = JwtUtil.createToken(jwtUser.getUsername(), roles, rememberMe.get());
        // Http Response Header 中返回 Token
        response.setHeader(JwtUtil.TOKEN_HEADER, token);

        //保存一周
        userInfoPojo.setUserId(jwtUser.getId());
        userInfoPojo.setUserName(jwtUser.getUsername());
        userInfoPojo.setRoles(roles);
        userInfoPojo.setImgAddress("");
        redisUtil.set(token, userInfoPojo, 60 * 24 * 7);

        System.out.println(jwtUser);
        Result result = new Result(RequestCodeEnum.OK.getState(), "登陆成功", userInfoPojo);
        response = addResultToResponse(response, result);
    }

    /**
     * 验证失败
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        Result result = new Result(RequestCodeEnum.ERROR.getState(), "登陆失败", authenticationException);
        response = addResultToResponse(response, result);
    }

    private HttpServletResponse addResultToResponse(HttpServletResponse response, Result result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        response.getWriter().println(jsonObject.toString());
        response.getWriter().flush();
        return response;
    }
}