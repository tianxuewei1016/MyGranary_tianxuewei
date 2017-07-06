package com.mygranary_tianxuewei.utils;

import android.content.Context;
import android.view.View;

import com.mygranary_tianxuewei.common.MyApplication;

/**
 * 作者：田学伟 on 2017/7/6 12:08
 * QQ：93226539
 * 作用：
 */

public class UiUtils {
    /*
    * 加载布局
    * */
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }
    /*
    * 返回一个上下文
    * */
    public static Context getContext() {

        return MyApplication.getContext();
    }
}
