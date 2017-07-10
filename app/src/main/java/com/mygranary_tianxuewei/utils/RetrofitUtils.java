package com.mygranary_tianxuewei.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：田学伟 on 2017/7/5 18:58
 * QQ：93226539
 * 作用：网络请求的工具类
 */

public class RetrofitUtils {
    //分类
    public static UserService getTypeFragmentAPI() {

        return createApi(UserService.class, ApiConstants.BASE_URL);
    }

    //品牌
    public static UserService getBrandFragmentAPI() {

        return createApi(UserService.class, ApiConstants.BASE_URL_BRAND);
    }

    //首页
    public static UserService getHomepagerFragmentAPI() {

        return createApi(UserService.class, ApiConstants.HOMEPAGER_URL);
    }

    //专题
    public static UserService getSpecialFragmentAPI() {

        return createApi(UserService.class, ApiConstants.SPECIAL_URL);
    }

//    //达人首页
//    public static UserService getEredarFragmentAPI() {
//
//        return createApi(UserService.class, ApiConstants.EREDAR_URL);
//    }


    /**
     * 根据传入的baseUrl，和api创建retrofit
     */
    private static <T> T createApi(final Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//新的配置
                .build();

        return retrofit.create(clazz);
    }
}
