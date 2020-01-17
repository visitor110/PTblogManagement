package com.pt.bloglib.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pt.bloglib.security.entity.JwtUser;
import com.pt.bloglib.dao.pojo.LoginUser;
import com.pt.bloglib.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LoginUser loginUser;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // 设置URL，以确定是否需要身份验证
        super.setFilterProcessesUrl("/user/login");
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse res)
            throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 从输入流中获取到登录的信息
           loginUser = objectMapper.readValue(request.getInputStream(), LoginUser.class);
            rememberMe.set(loginUser.getRemember());
            System.out.println("loginUser\t" + loginUser);
            // 这部分和attemptAuthentication方法中的源码是一样的，
            // 只不过由于这个方法源码的是把用户名和密码这些参数的名字是死的，所以我们重写了一下
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    loginUser.getUsername(), loginUser.getPassword());
            return authenticationManager.authenticate(authRequest);
        } catch (Exception e) {
            logger.error("request 中提取 LoginUser 失败\t", e);
            e.printStackTrace();
            return null;
        }

//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            // 从输入流中获取到登录的信息
//            LoginUser loginUser = objectMapper.readValue(request.getInputStream(), LoginUser.class);
//            rememberMe.set(loginUser.getRememberMe());
//            // 这部分和attemptAuthentication方法中的源码是一样的，
//            // 只不过由于这个方法源码的是把用户名和密码这些参数的名字是死的，所以我们重写了一下
//            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
//                    loginUser.getUsername(), loginUser.getPassword());
//            return authenticationManager.authenticate(authRequest);
//        } catch (IOException e) {
//            logger.error("resuest 中提取 LoginUser 失败\t", e);
//            e.printStackTrace();
//            return null;
//        }

//        User user = new User();
//        user.setUsername(req.getParameter("username").trim());
//        user.setPassword(req.getParameter("password"));
//        return authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//
    }

    /**
     * 如果验证成功，就生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) {

        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        List<String> roles = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // 创建 Token
        String token = JwtUtil.createToken(jwtUser.getUsername(), roles, rememberMe.get());
        // Http Response Header 中返回 Token
        response.setHeader(JwtUtil.TOKEN_HEADER, token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
    }

}