package com.mygranary_tianxuewei.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * 作者：田学伟 on 2017/7/5 18:54
 * QQ：93226539
 * 作用：当出现bug的时候自动退出软件
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private CrashHandler() {
    }

    private static CrashHandler crashHandler = new CrashHandler();

    public static CrashHandler getInstance() {
        return crashHandler;
    }

    private Context context;

    public void init(Context context) {
        //告诉系统 崩溃的操作 由我来执行
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.d("crash", "uncaughtException: " + e.getMessage());
        /**
         * 第一 提醒用户
         *第二 收集异常
         *第三 退出应用
         */
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                //执行在主线程中的代码
                Toast.makeText(context, "系统崩溃了！！！", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        collection(e);
        AppManager.getInstance().removeAll();
        //杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
        //参数 除了0以外都表示非正常退出
        System.exit(0);//退出虚拟机
    }

    /**
     * 收集异常信息
     *
     * @param e
     */
    private void collection(Throwable e) {

        String version = Build.BOARD;
    }
}


