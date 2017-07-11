package com.ovationtourism.fragment;


import android.support.v4.app.Fragment;

import com.ovationtourism.R;
import com.ovationtourism.base.BaseFragment;


/**
 * @name OvationTourism
 * @class name：com.ovationtourism.fragment
 * @anthor hx
 * @time 2017-05-09 10:41
 * @change time
 * @class describe
 */
public class FragmentFactory {
    public static Fragment getInstanceByIndex(int index) {
        BaseFragment fragment = null;
        switch (index) {
            case R.id.rb_homepage:
                fragment=new HomePageFragment();
                break;
            case R.id.rb_tourism:
                fragment=new TourismFragment();
                break;
            case R.id.rb_talent:
                fragment=new TalentFragment();
                break;
            case R.id.rb_service:
                fragment=new CustomerServiceFragment();
                break;
            case R.id.rb_mine:
                fragment=new MineFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

}
