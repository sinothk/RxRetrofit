package com.sinothk.rxretrofit;


import androidx.annotation.NonNull;

import com.sinothk.rxretrofit.interceptor.RetrofitLogInterceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : 梁玉涛
 * e-mail : 381518188@qq.com
 * date   : 2018/11/1911:02
 * desc   : 弃用，使用 RxRetrofit 代替！
 */
@Deprecated
public class RetrofitFactory {

    // =========================================================================
    @Deprecated
    public static Retrofit init(String baseUrl) {
        // 创建网络请求接口的实例
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * 初始化
     *
     * @param baseUrl       服务器地址
     * @param headerDataMap 请求头键值对
     * @return
     */
    @Deprecated
    public static Retrofit init(String baseUrl, HashMap<String, String> headerDataMap) {
        if (headerDataMap != null && !headerDataMap.isEmpty()) {

            OkHttpClient okHttpClient = createHeaders(headerDataMap);

            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build();

        } else {
            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build();
        }
    }

    /**
     * 根据请求头数据键值对，创建头部
     *
     * @param headerDataMap 请求头键值对
     * @return
     */
    @Deprecated
    private static OkHttpClient createHeaders(HashMap<String, String> headerDataMap) {

        final Headers headers = Headers.of(headerDataMap);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {

                Request original = chain.request();

                Request request = original.newBuilder()
                        .headers(headers)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }


        });

        return httpClient.build();
    }

    /**
     * 在子线程中运行
     *
     * @param baseUrl
     * @param executorService
     * @return
     */
    @Deprecated
    public static Retrofit init(String baseUrl, ExecutorService executorService) {
        // 创建网络请求接口的实例
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .callbackExecutor(executorService)
                .build();
    }

    // ===============================================


    //    public static Retrofit init2() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("https://www.sojson.com/")
//                .build();
//        return retrofit;
//    }

    // ===============================================
//    public static Retrofit init() {
//        // 创建网络请求接口的实例
//        return new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http://192.168.124.26:8888/")
//                .client(createHeader())
//                .build();
//    }
//
//    private static OkHttpClient createHeader() {
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
//                Request original = chain.request();
//
//                Request request = original.newBuilder()
//                        .header("token", "112233445566778899")
//                        .header("userCode", "381518188")
//                        .header("userName", "LiangYT")
//                        .method(original.method(), original.body())
//                        .build();
//                return chain.proceed(request);
//            }
//        });
//
//        return httpClient.build();
//    }
}
