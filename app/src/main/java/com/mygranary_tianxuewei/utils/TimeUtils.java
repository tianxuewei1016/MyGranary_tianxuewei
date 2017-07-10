package com.mygranary_tianxuewei.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：田学伟 on 2017/7/10 15:44
 * QQ：93226539
 * 作用：获取时间的工具类
 */

public class TimeUtils {
    public static String getCurrentTime(){
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(currentTimeMillis);
        String format = simpleDateFormat.format(date);
        return format;
    }
}

