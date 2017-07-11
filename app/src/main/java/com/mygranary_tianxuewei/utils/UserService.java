package com.mygranary_tianxuewei.utils;

import com.mygranary_tianxuewei.bean.BrandFragmentBean;
import com.mygranary_tianxuewei.bean.HomepagerBean;
import com.mygranary_tianxuewei.bean.SpecialBean;
import com.mygranary_tianxuewei.bean.TypeFragmentBean;

import retrofit2.http.GET;
import rx.Observable;

import static com.mygranary_tianxuewei.utils.ApiConstants.BRAND_FRAGMEN;
import static com.mygranary_tianxuewei.utils.ApiConstants.HOMEPAGER_FRAGMENT;
import static com.mygranary_tianxuewei.utils.ApiConstants.SPECIAL_FRAGMENT;
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

    @GET(SPECIAL_FRAGMENT)
    Observable<SpecialBean> getSpecialFragmentInfo();

    @GET(HOMEPAGER_FRAGMENT)
    Observable<HomepagerBean> getHomepagerFragmentInfo();

//    @GET("user/masterListInfo?app_key=Android&count=10&owner_id=\" {id} \"&page=\" {page} \"&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0")
//    Observable<LikeAndRecommendBean> getEredarFragmentInfo(@Path("id") String id, @Query("page") int page);
}
