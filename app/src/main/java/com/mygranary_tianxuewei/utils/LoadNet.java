package com.mygranary_tianxuewei.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 作者：田学伟 on 2017/7/7 15:55
 * QQ：93226539
 * 作用：联网请求的工具类
 */

public class LoadNet {

    private static String COOKIE = "cookie";

    public static void getDataPost(String url, Context context, final LoadNetHttp http) {
        Log.e("TAG", "getDataPost:直接向服务器发起请求数据");
        OkHttpUtils.post()
                .url(url)
                .tag(context)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        Log.e("TAG", "加载失败" + e.getMessage());
                        http.failure(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            Log.e("TAG", "加载成功");
                            http.success(response);
                        }
                    }
                });
    }

    public static void getDataPost(String url, final Context context, Map<String, String> map, final LoadNetHttp http) {

        StringBuilder tempParams = new StringBuilder();
        int pos = 0;
        for (String key : map.keySet()) {
            if (pos > 0) {
                tempParams.append("&");
            }
            try {
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(map.get(key), "utf-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            pos++;
        }
        String params = tempParams.toString();
        Log.d("params", "params:" + params);
        if (map != null && !map.isEmpty()) {
            OkHttpUtils.getInstance()
                    .postString()
                    .url(url)
                    .mediaType(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"))
                    .content(params)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int i) {
                            Log.d("fail", "" + e);
                            http.failure(e.getMessage());
                        }

                        @Override
                        public void onResponse(String s, int i) {
                            Log.d("succeed", "" + s);
                            http.success(s);
                        }
                    });


        } else {
            getDataPost(url, context, http);
        }

    }

    public static void getDataPostFile(String url, final Context context, String path, final LoadNetHttp http) {
        File file = new File(Environment.getExternalStorageDirectory(), path);
        if (!file.exists()) {
//            Toast.makeText(context, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
//            return;
        }
        if (file != null) {
            OkHttpUtils.getInstance()
                    .post()
                    .addFile("icon", path, file)
                    .url(url)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int i) {
                            Log.d("fail", "" + e);
                            http.failure(e.getMessage());
                        }

                        @Override
                        public void onResponse(String s, int i) {
                            Log.d("succeed", "" + s);
                            http.success(s);
                        }
                    });
        } else {
            getDataPost(url, context, http);
        }
    }

    public static void savePreference(Context context, String key, String value) {

        OkHttpUtils.postString().build().getRequest().headers().toString();


    }

    private static ProgressBar mProgressBar;

    public static void loadFile(Context context, String url) {
        OkHttpUtils
                .post()
                .url(url)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "gson-2.2.1.jar")//
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(File response, int id) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setDataAndType(Uri.fromFile(response),
                                "application/vnd.android.package-archive");
//                        startActivityForResult(intent, 0);
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        mProgressBar.setProgress((int) (100 * progress));
                    }
                });
    }
}



