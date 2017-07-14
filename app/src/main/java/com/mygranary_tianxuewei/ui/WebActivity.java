package com.mygranary_tianxuewei.ui;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;

import butterknife.Bind;

/**
 * 点击杂志页面的Activity
 */
public class WebActivity extends BaseActivity {

    @Bind(R.id.mag_web_writ)
    TextView magWebWrit;
    @Bind(R.id.mag_web_type)
    ImageView magWebType;
    @Bind(R.id.mag_webview)
    WebView magWebview;
    @Bind(R.id.mag_progressbar)
    ProgressBar magProgressbar;
    private WebSettings settings;
    private String url;
    private String name;


    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initTitle() {
        //接受数据
        url = getIntent().getStringExtra("url");
        name = getIntent().getStringExtra("name");
    }

    @Override
    protected void initData() {
        settings = magWebview.getSettings();
        //设置相关配置
        //设置支持javaScript
        settings.setJavaScriptEnabled(true);
        //设置双击页面变大变小
        settings.setUseWideViewPort(true);
        //添加变大变小按钮
        settings.setBuiltInZoomControls(true);
        magWebWrit.setText(name);
        magWebview.loadUrl(url);
    }

    @Override
    protected void initListener() {
        magWebType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.right_in_ac, R.anim.left_out_ac);
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.right_in_ac, R.anim.left_out_ac);
        }
        return super.onKeyUp(keyCode, event);
    }
}
