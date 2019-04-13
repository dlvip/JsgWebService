package com.old.time.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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

    /**
     * @param string
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String encodeByMd5(String string) {
        // 确定计算方法
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Base64.Encoder base64Encoder = Base64.getEncoder();
        // 加密字符串
        try {
            return base64Encoder.encodeToString(md5.digest(string.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "E10ADC3949BA59ABBE56E057F20F883E";//123456
    }

    /**
     * 融云key
     */
    public static final String RONG_APP_KEY = "x18ywvqfxcbjc";

    /**
     * 融云秘钥
     */
    public static final String RONG_APP_SECRET = "x18ywvqfxcbjc";
}
