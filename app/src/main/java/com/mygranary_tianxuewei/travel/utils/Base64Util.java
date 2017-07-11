package com.mygranary_tianxuewei.travel.utils;

import android.util.Base64;

/**
 * 作者：田学伟 on 2017/7/11 14:31
 * QQ：93226539
 * 作用：
 */

public class Base64Util {
    /**
     * base64解密
     *
     * @param str
     * @return
     */
    public static String jie(String str) {
        String base64jie = new String(Base64.decode(str.getBytes(), Base64.DEFAULT));
        return base64jie;
    }

    /**
     * Base64加密
     *
     * @param str
     * @return
     */
    public static String add(String str) {

        byte[] bs = str.getBytes();
        String base64jia = new String(Base64.encode(bs, Base64.DEFAULT));
        return base64jia;
    }
}

