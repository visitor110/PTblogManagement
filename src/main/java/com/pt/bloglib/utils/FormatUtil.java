package com.pt.bloglib.utils;

public class FormatUtil {

    public static boolean checkStringIsNull(String... strs) {
        for (String str : strs) {
            if (str == null || "".equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkClassIsNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }
}
