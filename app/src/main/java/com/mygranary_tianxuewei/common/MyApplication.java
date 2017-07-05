package com.mygranary_tianxuewei.common;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * 作者：田学伟 on 2017/7/5 18:52
 * QQ：93226539
 * 作用：初始化
 */

public class MyApplication extends Application {

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化logger打日志工具
        Logger.addLogAdapter(new AndroidLogAdapter());

        //初始化LeakCanary内存泄露检测工具
        initLeak();

        //初始化crashHandler,当上线的时候开启,当出现bug的时候退出软件
//        CrashHandler.getInstance().init(this);

        //监听捕获异常
        Crashabnormal instance = Crashabnormal.getInstance();
        instance.init(getApplicationContext());
        //初始化Stetho调试工具
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    private void initLeak() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        } else {
            refWatcher = LeakCanary.install(this);
        }
    }
}


