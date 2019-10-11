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
public class RetrofitLogInterceptor implements Interceptor {

    private static String TAG = "RxRetrofitLog";

    @Override
    public synchronized okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
        Log.d(TAG, "RxRetrofit > ============================================");
        // 时间
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        Log.d(TAG, "RxRetrofit > 耗时: " + duration + "毫秒");

        // 请求地址
        Request request = chain.request();
        Log.d(TAG, "RxRetrofit > 地址: " + request.url());

        // 参数
        printParams(request);

        // 结果
        String content;
        try {
            content = response.body().string();
        } catch (Exception e) {
            content = "";
        }
        Log.d(TAG, "RxRetrofit > 结果: \n" + content);
        Log.d(TAG, "RxRetrofit > ============================================");

        okhttp3.MediaType mediaType = response.body().contentType();
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
    }

    private void printParams(Request request) {
        try {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);

            Charset charset = Charset.forName("UTF-8");

            MediaType contentType = request.body().contentType();

            if (contentType != null) {
                charset = contentType.charset(UTF_8);
            }

            String params = buffer.readString(charset);
            Log.d(TAG, "RxRetrofit > 参数: \n" + params);

        } catch (Exception e) {
            Log.d(TAG, "RxRetrofit > 参数: 无参数");
        }
    }
}
