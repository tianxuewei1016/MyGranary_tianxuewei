package com.mygranary_tianxuewei.utils;

import com.mygranary_tianxuewei.bean.BrandFragmentBean;
import com.mygranary_tianxuewei.bean.TypeFragmentBean;

import retrofit2.http.GET;
import rx.Observable;

import static com.mygranary_tianxuewei.utils.ApiConstants.BRAND_FRAGMEN;
import static com.mygranary_tianxuewei.utils.ApiConstants.TYPE_FRAGMENT;

/**
 * 作者：田学伟 on 2017/7/6 09:50
 * QQ：93226539
 * 作用：
 */

public interface UserService {
    @GET(TYPE_FRAGMENT)
    Observable<TypeFragmentBean> getTypeFragmentInfo();

    @GET(BRAND_FRAGMEN)
    Observable<BrandFragmentBean> getBrandFragmentInfo();
}
