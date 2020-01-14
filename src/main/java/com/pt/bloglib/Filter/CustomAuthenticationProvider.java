package com.pt.bloglib.Filter;

import com.pt.bloglib.security.utils.UserPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 登录认证器，负责将登录过滤器拦截得到的登录账号密码进行验证。
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService service;

    private UserPasswordEncoder encoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
        this.service = userDetailsService;
    }

    /**
     * 验证类
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        User userDetails = null;
        if (username != null) {
            //调用相应service从数据库获取对应用户信息
            userDetails = (User) service.loadUserByUsername(username);
        }

        if (userDetails == null) {
            throw new UsernameNotFoundException("用户名或密码无效");
        } else if (!userDetails.isEnabled()) {
            throw new DisabledException("用户已被禁用");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("账号已过期");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("账号已被锁定");
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new LockedException("凭证已过期");
        }

        String password = userDetails.getPassword();
//        if(!password.equals(.encodeByMD5((String)token.getCredentials()))) {
//            throw new BadCredentialsException("用户名/密码无效");
//        }
        if (!encoder.matches((String) token.getCredentials(), password)) {
            throw new BadCredentialsException("用户名/密码无效");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

    @Autowired
    public void setEncoder(UserPasswordEncoder encoder) {
        this.encoder = encoder;
    }

}