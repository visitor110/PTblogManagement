package com.pt.bloglib.security.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义加密算法，使用MD5加密，继承PasswordEncoder。
 * 在WebSecurityConfig中替换原先的BCryptPasswordEncoder
 */
public class UserPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return MD5Util.encode((String) charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(MD5Util.encode((String) charSequence));
    }
}
