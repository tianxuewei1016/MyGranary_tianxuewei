package com.mygranary_tianxuewei.ui;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;

import butterknife.Bind;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.welcome_iv_icon)
    ImageView welcomeIvIcon;
    @Bind(R.id.activity_welcome)
    RelativeLayout activityWelcome;
    private Handler handler = new Handler();

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initTitle() {
        Glide.with(this).load(R.drawable.loading_start).into(welcomeIvIcon);
    }

    @Override
    protected void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMyActivity();
            }
        }, 6000);
    }

    @Override
    protected void initListener() {
        activityWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacksAndMessages(null);
                startMyActivity();
            }
        });
    }

    private void startMyActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
