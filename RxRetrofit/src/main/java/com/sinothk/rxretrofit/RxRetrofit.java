package com.sinothk.rxretrofit;

import android.util.Log;

import com.sinothk.rxretrofit.interceptor.DownLoadInterceptor;
import com.sinothk.rxretrofit.interceptor.LogHeaderInterceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/24 on 23:55
 *  项目:  RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public class RxRetrofit {

    /**
     * 无请求头请求
     *
     * @param baseUrl
     * @return
     */
    public static Retrofit init(String baseUrl) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(new LogHeaderInterceptor(true)
                ).build();

        // 创建网络请求接口的实例
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)//此client是为了打印信息
                .build();
    }

    /**
     * 带请求头请求
     *
     * @param baseUrl   服务器地址
     * @param headerMap 请求头键值对
     * @return
     */
    public static Retrofit init(String baseUrl, final HashMap<String, String> headerMap) {
        if (headerMap == null || headerMap.isEmpty()) {
            return init(baseUrl);
        } else {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new LogHeaderInterceptor(headerMap, true));

            OkHttpClient okHttpClient = httpClient.build();

            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build();
        }
    }

    /**
     * 在子线程中运行
     *
     * @param baseUrl
     * @param executorService
     * @return
     */
    public static Retrofit init(String baseUrl, ExecutorService executorService) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(new DownLoadInterceptor()).build();

        // 创建网络请求接口的实例
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)//此client是为了打印信息
                .callbackExecutor(executorService)
                .build();
    }
}



