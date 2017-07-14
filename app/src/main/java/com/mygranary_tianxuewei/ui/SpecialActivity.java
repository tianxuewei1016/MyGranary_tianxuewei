package com.mygranary_tianxuewei.ui;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.bean.SpecialBean;
import com.mygranary_tianxuewei.utils.ConstantUtil;

import butterknife.Bind;

/**
 * 专题点击事件的Activity
 */
public class SpecialActivity extends BaseActivity {

    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.iv_web_type)
    ImageView ivWebType;
    @Bind(R.id.tv_web_writ)
    TextView tvWebWrit;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    private WebSettings settings;
    private SpecialBean.DataBean.ItemsBean special_bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_special;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {
        special_bean = (SpecialBean.DataBean.ItemsBean) getIntent().getSerializableExtra(ConstantUtil.SPECIAL_BEAN);
        settings = webview.getSettings();
        //设置相关配置
        //设置支持javaScript
        settings.setJavaScriptEnabled(true);
        //设置双击页面变大变小
        settings.setUseWideViewPort(true);
        //添加变大变小按钮
        settings.setBuiltInZoomControls(true);
    }

    @Override
    protected void initListener() {
        //加载网页地址
        webview.loadUrl(special_bean.getTopic_url());
        tvWebWrit.setText(special_bean.getTopic_name());
        //设置加载页面完成的监听
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                try {
                    progressbar.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ivWebType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.right_in_ac, R.anim.left_out_ac);
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.right_in_ac, R.anim.left_out_ac);
        }
        return super.onKeyUp(keyCode, event);
    }
}
