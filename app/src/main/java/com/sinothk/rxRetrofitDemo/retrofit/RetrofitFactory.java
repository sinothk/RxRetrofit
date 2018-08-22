package com.sinothk.rxRetrofitDemo.retrofit;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
//        return retrofit;
//    }


    public static Retrofit init() {
        // 创建网络请求接口的实例
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.124.16:8888/")
                .client(createHeader())
                .build();
    }

    private static OkHttpClient createHeader() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("User-Agent", "Your-App-Name")
                        .header("Accept", "application/vnd.yourapi.v1.full+json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        return httpClient.build();
    }
}
