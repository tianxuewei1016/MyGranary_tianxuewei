package com.mygranary_tianxuewei.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.bean.LikeAndRecommendBean;
import com.mygranary_tianxuewei.fragment.eredar.AttentionFragment;
import com.mygranary_tianxuewei.fragment.eredar.FansFragment;
import com.mygranary_tianxuewei.fragment.eredar.LikeFragment;
import com.mygranary_tianxuewei.fragment.eredar.NominateFragment;
import com.mygranary_tianxuewei.utils.ApiConstants;
import com.mygranary_tianxuewei.utils.CircleImageView;
import com.mygranary_tianxuewei.utils.HttpUtils;
import com.mygranary_tianxuewei.utils.JsonCallBack;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 点击达人页面跳转过来的Activity
 */
public class UserInfoActivity extends BaseActivity implements JsonCallBack {

    @Bind(R.id.tv_user_writ)
    TextView tvUserWrit;
    @Bind(R.id.iv_user_type)
    ImageView ivUserType;
    @Bind(R.id.user_head_img)
    CircleImageView userHeadImg;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.user_job)
    TextView userJob;
    @Bind(R.id.user_msg)
    TextView userMsg;
    @Bind(R.id.user_forcus)
    TextView userForcus;
    @Bind(R.id.user_radio_bt_like)
    RadioButton userRadioBtLike;
    @Bind(R.id.user_radio_bt_recommend)
    RadioButton userRadioBtRecommend;
    @Bind(R.id.user_radio_bt_focus)
    RadioButton userRadioBtFocus;
    @Bind(R.id.user_radio_bt_fans)
    RadioButton userRadioBtFans;
    @Bind(R.id.user_radio_group)
    RadioGroup userRadioGroup;
    @Bind(R.id.user_fram)
    FrameLayout userFram;
    private String id;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initTitle() {
        //接收id
        id = getIntent().getExtras().getString("id");
    }

    @Override
    protected void initData() {
        //填充数据
        HttpUtils.load(ApiConstants.getUserLike(id, 1)).callBack(this, 1);
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new LikeFragment(id));
        fragments.add(new NominateFragment(id));
        fragments.add(new AttentionFragment(id));
        fragments.add(new FansFragment(id));

    }

    @Override
    protected void initListener() {
        userRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.user_radio_bt_like:
                        position = 0;
                        break;
                    case R.id.user_radio_bt_recommend:
                        position = 1;
                        break;
                    case R.id.user_radio_bt_focus:
                        position = 2;
                        break;
                    case R.id.user_radio_bt_fans:
                        position = 3;
                        break;
                }
                //根据位置切换到对应的Fragment
                BaseFragment currentFragment = fragments.get(position);
                switchFragment(currentFragment);
            }
        });
        userRadioGroup.check(R.id.user_radio_bt_recommend);

        ivUserType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.right_in_ac, R.anim.left_out_ac);
            }
        });
    }

    private void switchFragment(BaseFragment currentFragment) {
        //切换的不是同一个页面
        if (tempFragment != currentFragment) {
            //得到FragmentMager
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //如果没有添加就添加
            if (!currentFragment.isAdded()) {
                //缓存的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //添加
                ft.add(R.id.user_fram, currentFragment);
            } else {
                //缓存的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //显示
                ft.show(currentFragment);
            }
            //事务提交
            ft.commit();
            //把当前的赋值成缓存的
            tempFragment = currentFragment;
        }
    }

    /**
     * 请求到数据的时候回调回来的接口
     *
     * @param result
     * @param requestCode
     */
    @Override
    public void successJson(String result, int requestCode) {
        if (requestCode == 1) {
            LikeAndRecommendBean bean = new Gson().fromJson(result, LikeAndRecommendBean.class);
            LikeAndRecommendBean.DataBean.ItemsBean items = bean.getData().getItems();

            userJob.setText(items.getUser_desc());
            userName.setText(items.getUser_name());
            tvUserWrit.setText(items.getUser_name());
            Glide.with(this)
                    .load(items.getUser_image().getOrig())
                    .into(userHeadImg);

            userRadioBtLike.setText("喜欢\n" + items.getLike_count());
            userRadioBtRecommend.setText("推荐\n" + items.getRecommendation_count());
            userRadioBtFocus.setText("关注\n" + items.getFollowing_count());
            userRadioBtFans.setText("粉丝\n" + items.getFollowed_count());
        }

        if (requestCode == 2) {
            LikeAndRecommendBean bean = new Gson().fromJson(result, LikeAndRecommendBean.class);
            LikeAndRecommendBean.DataBean.ItemsBean items = bean.getData().getItems();

            userJob.setText(items.getUser_desc());
            userName.setText(items.getUser_name());
            tvUserWrit.setText(items.getUser_name());
            Glide.with(this)
                    .load(items.getUser_image().getOrig())
                    .into(userHeadImg);

            userRadioBtLike.setText("喜欢\n" + items.getLike_count());
            userRadioBtRecommend.setText("推荐\n" + items.getRecommendation_count());
            userRadioBtFocus.setText("关注\n" + items.getFollowing_count());
            userRadioBtFans.setText("粉丝\n" + items.getFollowed_count());
        }
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
