package com.pt.bloglib.utils;

import java.util.Random;

/**
 * 生成验证码
 */
public class VerifyCodeUtil {

    private static final String RANDOMSTR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String registerMailVerifyCode(Integer codeLength){
        Random random = new Random();
        String randomString = "";
        for (int i = 1; i <= codeLength; i++) {
            randomString += RANDOMSTR.charAt(random.nextInt(RANDOMSTR.length()));
        }
        return randomString;
    }
}
