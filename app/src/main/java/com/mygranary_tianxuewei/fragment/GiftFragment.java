package com.mygranary_tianxuewei.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.ui.GiftActivity;
import com.mygranary_tianxuewei.ui.MainActivity;

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
    private String url;
    private String url2;
    private String url3;
    private String url4;
    private String url5;
    private String url6;
    private String url7;

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
        MainActivity mainActivity = (MainActivity) context;
        switch (view.getId()) {
            case R.id.iv1_gift_home:
                Intent intent1 = new Intent(context, GiftActivity.class);
                url = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=7&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
                intent1.putExtra("url1", url);
                startActivity(intent1);
                mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.iv2_gift_home:
                Intent intent2 = new Intent(context, GiftActivity.class);
                url2 = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=1&page=1&sig=DFD7151CC9D607E396FE108FE270FFF3%7C366534120395468&v=1.0";
                intent2.putExtra("url2", url2);
                startActivity(intent2);
                mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.iv3_gift_home:
                Intent intent3 = new Intent(context, GiftActivity.class);
                url3="http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=2&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
                intent3.putExtra("url3", url3);
                startActivity(intent3);
                mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.iv4_gift_home:
                Intent intent4 = new Intent(context, GiftActivity.class);
                url4="http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=3&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
                intent4.putExtra("url4", url4);
                startActivity(intent4);
                mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.iv5_gift_home:
                Intent intent5 = new Intent(context, GiftActivity.class);
                url5="http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=4&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
                intent5.putExtra("url5", url5);
                startActivity(intent5);
                mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.iv6_gift_home:
                Intent intent6 = new Intent(context, GiftActivity.class);
                url6="http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=5&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
                intent6.putExtra("url6", url6);
                startActivity(intent6);
                mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.iv7_gift_home:
                Intent intent7 = new Intent(context, GiftActivity.class);
                url7 = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=6&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
                intent7.putExtra("url7", url7);
                startActivity(intent7);
                mainActivity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
        }
    }
}
