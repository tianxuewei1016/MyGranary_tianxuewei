package com.mygranary_tianxuewei.ui;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.mygranary_tianxuewei.R;
import com.mygranary_tianxuewei.base.BaseActivity;
import com.mygranary_tianxuewei.base.BaseFragment;
import com.mygranary_tianxuewei.common.AppManager;
import com.mygranary_tianxuewei.fragment.ShareFragment;
import com.mygranary_tianxuewei.fragment.ShopFragment;
import com.mygranary_tianxuewei.fragment.eredar.EredarFragment;
import com.mygranary_tianxuewei.fragment.magazine.MagazineFragment;
import com.mygranary_tianxuewei.travel.MineFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.rg_tab)
    RadioGroup rgTab;
    /**
     * 装各个Fragment的集合
     */
    private ArrayList<BaseFragment> fragments;
    /**
     * 之前显示的Fragment
     */
    private Fragment tempFragment;
    /**
     * 被选中页面的位置
     */
    private int position;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {
        //Android6.0动态获取权限
        isGrantExternalRW(this);
    }

    /**
     * 解决安卓6.0以上版本不能读取外部存储权限的问题
     *
     * @param activity
     * @return
     */
    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
            return false;
        }
        return true;
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new ShopFragment());      //商城
        fragments.add(new MagazineFragment());  //杂志
        fragments.add(new EredarFragment());   //达人
        fragments.add(new ShareFragment());     //分享
        fragments.add(new MineFragment());      //个人中心
    }

    @Override
    protected void initListener() {
        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_homepage:
                        position = 0;
                        break;
                    case R.id.rb_tourism:
                        position = 1;
                        break;
                    case R.id.rb_talent:
                        position = 2;
                        break;
                    case R.id.rb_service:
                        position = 3;
                        break;
                    case R.id.rb_mine:
                        position = 4;
                        break;
                }
                Fragment currentFragment = getFragment(position);//HomeFragment
                switchFragment(currentFragment);
            }
        });
        //默认选中首页-放在setOnCheckedChangeListener 执行之后
        rgTab.check(R.id.rb_homepage);
    }

    private Fragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            return fragments.get(position);
        }
        return null;
    }

    private void switchFragment(Fragment currentFragment) {
        if (tempFragment != currentFragment) {

            if (currentFragment != null) {
                //开启事务
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (!currentFragment.isAdded()) {
                    //隐藏之前显示的
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    //判断currentFragment 有没有添加，如果没有就添加
                    ft.add(R.id.content, currentFragment);
                } else {
                    //隐藏之前显示的
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    //否则就显示
                    ft.show(currentFragment);
                }
                ft.commit();//统一提交
            }
            tempFragment = currentFragment;
        }
    }

    /*
    * 双击退出
    * */
    private boolean isExit = false;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (isExit) {
                finish();
            }
            showToast("再按一次退出程序");
            isExit = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }
}
