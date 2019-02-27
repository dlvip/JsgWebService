package com.old.time.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenerateShortUuid {

    /**
     * 生成userid格式
     *
     * @param mobile
     * @return
     */
    public static String getPhoneUserId(String mobile) {
        String userId;
        long currentTime = System.currentTimeMillis();
        userId = String.valueOf(currentTime);

        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat("yyyy-M-dd HH:mm:ss").parse("2019-01-01 00:00:00"));
            currentTime = currentTime - c.getTimeInMillis();
            userId = currentTime + mobile.substring(7, 11);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return userId;
    }
}
