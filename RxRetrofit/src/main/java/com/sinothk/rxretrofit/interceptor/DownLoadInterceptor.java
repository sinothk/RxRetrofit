package com.sinothk.rxretrofit.interceptor;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okio.Buffer;

import static okhttp3.internal.Util.UTF_8;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/25 on 0:00
 *  项目:  RxRetrofitLib
 *  描述:  
 *  更新:
 * <pre>
 */
public class DownLoadInterceptor implements Interceptor {

    private static String TAG = "RxRetrofitLog";

    @Override
    public synchronized okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
        // 日志打印封装
        String TAG = "RxRetrofitLog";
        Log.d(TAG, "RxRetrofit > ============================================");
        // 请求地址
        Request request = chain.request();
        Log.d(TAG, "RxRetrofit > 地址: " + request.url());
        // 结果
        Log.d(TAG, "RxRetrofit > ============================================");
        return chain.proceed(chain.request());
    }
}
