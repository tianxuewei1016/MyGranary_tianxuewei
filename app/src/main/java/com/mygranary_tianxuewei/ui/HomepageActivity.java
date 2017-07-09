package com.mygranary_tianxuewei.ui;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.bean.HomepagerBean;
import com.mygranary_tianxuewei.utils.ConstantUtil;

import butterknife.Bind;

/**
 * 首页点击事件的Activity
 */
public class HomepageActivity extends BaseActivity {

    @Bind(R.id.tv_web_writ)
    TextView tvWebWrit;
    @Bind(R.id.iv_web_type)
    ImageView ivWebType;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    private WebSettings settings;
    private HomepagerBean.DataBean.ItemsBean.ListBean home_pager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_homepage;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {
        home_pager = (HomepagerBean.DataBean.ItemsBean.ListBean) getIntent().getSerializableExtra(ConstantUtil.HOME_PAGER);

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
        webview.loadUrl(home_pager.getOne().getTopic_url());
        tvWebWrit.setText(home_pager.getOne().getTopic_name());

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
            }
        });
    }
}
