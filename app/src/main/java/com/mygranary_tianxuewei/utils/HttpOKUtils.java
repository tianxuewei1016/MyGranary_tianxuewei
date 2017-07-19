package com.mygranary_tianxuewei.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 作者：田学伟 on 2017/7/16 13:30
 * QQ：93226539
 * 作用：
 */

public class HttpOKUtils {
    private HttpOKUtils() {
    }

    private static HttpOKUtils httpUtils = new HttpOKUtils();

    public static HttpOKUtils getInstance() {
        return httpUtils;
    }

    /*
        封装第三方联网框架
        * get联网请求
        * */
    public void get(String url, final MyHttpClickListener httpListener) {

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (httpListener != null) {

                            httpListener.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        if (httpListener != null) {

                            httpListener.onSuccess(response);
                        }
                    }
                });
    }

    /**
     * 利用接口将联网数据回传回去
     */
    public interface MyHttpClickListener {

        void onSuccess(String content);

        void onFailure(String content);

    }
}

