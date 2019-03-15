package com.old.time.utils;

import java.util.regex.Pattern;

public class StringUtils {

    /**
     * 正则表达式判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");

        return pattern.matcher(str).matches();
    }
}
