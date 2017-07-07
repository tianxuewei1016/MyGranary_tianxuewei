package com.mygranary_tianxuewei.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：田学伟 on 2017/7/5 23:15
 * QQ：93226539
 * 作用：礼物
 */

public class GiftFragment extends BaseFragment {
    @Bind(R.id.iv1_gift_home)
    ImageView iv1GiftHome;
    @Bind(R.id.iv2_gift_home)
    ImageView iv2GiftHome;
    @Bind(R.id.iv3_gift_home)
    ImageView iv3GiftHome;
    @Bind(R.id.iv4_gift_home)
    ImageView iv4GiftHome;
    @Bind(R.id.iv5_gift_home)
    ImageView iv5GiftHome;
    @Bind(R.id.iv6_gift_home)
    ImageView iv6GiftHome;
    @Bind(R.id.iv7_gift_home)
    ImageView iv7GiftHome;
    @Bind(R.id.tv_gift_home)
    TextView tvGiftHome;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv1_gift_home, R.id.iv2_gift_home, R.id.iv3_gift_home, R.id.iv4_gift_home, R.id.iv5_gift_home, R.id.iv6_gift_home, R.id.iv7_gift_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv1_gift_home:
                Toast.makeText(context, "精选被点击了...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv2_gift_home:
                Toast.makeText(context, "节日被点击了...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv3_gift_home:
                Toast.makeText(context, "爱情被点击了...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv4_gift_home:
                Toast.makeText(context, "生日被点击了...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv5_gift_home:
                Toast.makeText(context, "朋友被点击了...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv6_gift_home:
                Toast.makeText(context, "孩子被点击了...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv7_gift_home:
                Toast.makeText(context, "父母被点击了...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
