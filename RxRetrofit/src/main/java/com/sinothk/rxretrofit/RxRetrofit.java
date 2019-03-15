package com.sinothk.rxretrofit;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.sinothk.rxretrofit.converterFactorys.FastJsonConverterFactory;
import com.sinothk.rxretrofit.interceptor.DownLoadInterceptor;
import com.sinothk.rxretrofit.interceptor.LogHeaderInterceptor;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

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
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .retryOnConnectionFailure(false)
                .addInterceptor(new LogHeaderInterceptor(true)
                ).build();

        // 创建网络请求接口的实例
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(new Retrofit2ConverterFactory())
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

            httpClient.connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES);

            OkHttpClient okHttpClient = httpClient.build();

            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(new Retrofit2ConverterFactory())//FastJsonConverterFactory.create() GsonConverterFactory
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
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .retryOnConnectionFailure(false)
                .addInterceptor(new DownLoadInterceptor()).build();

        // 创建网络请求接口的实例
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create()) //GsonConverterFactory
                .baseUrl(baseUrl)
                .client(okHttpClient)//此client是为了打印信息
                .callbackExecutor(executorService)
                .build();
    }
}



