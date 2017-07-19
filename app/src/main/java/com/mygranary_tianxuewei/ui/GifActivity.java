package com.mygranary_tianxuewei.ui;

import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoView;

/**
 * 图片的放大缩小
 */
public class GifActivity extends BaseActivity {
    @Bind(R.id.iv_image_gif)
    PhotoView ivImageGif;
    @Bind(R.id.activity_show_image_and_gif)
    LinearLayout activityShowImageAndGif;
    private String url;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gif;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("url");
        Glide.with(this)
                .load(url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(ivImageGif);
    }

    @Override
    protected void initListener() {

    }
}
