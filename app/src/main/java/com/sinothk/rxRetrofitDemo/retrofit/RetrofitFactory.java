package com.sinothk.rxRetrofitDemo.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *  创建:  LiangYT 2018/6/23/023 on 0:48
 *  项目: RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public class RetrofitFactory {


//    public static Retrofit init2() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("https://www.sojson.com/")
//                .build();
//
//
//        return retrofit;
//    }


    public static Retrofit init() {
        // 创建网络请求接口的实例
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.2.135:8080/")
                .build();
    }
}
