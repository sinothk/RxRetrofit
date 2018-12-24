package com.sinothk.rxretrofit;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.internal.Util.UTF_8;

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
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        // 日志打印封装
                        printRxRetrofitLog(chain, false);

                        return chain.proceed(chain.request());
                    }
                }).build();

        // 创建网络请求接口的实例
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)//此client是为了打印信息
                .callbackExecutor(executorService)
                .build();
    }

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
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        // 日志打印封装
                        printRxRetrofitLog(chain, true);
                        return chain.proceed(chain.request());
                    }
                }).build();

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
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    // 请求部分
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .headers(Headers.of(headerMap))
                            .method(original.method(), original.body())
                            .build();

                    // =========== 日志部分 ======================================
                    printRxRetrofitLog(chain, true);
//                    okhttp3.MediaType mediaType = response.body().contentType();
//                    return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
                    return chain.proceed(request);
                }
            });

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
     * 打印日志
     *
     * @param chain
     * @throws IOException
     */
    private static void printRxRetrofitLog(Interceptor.Chain chain, boolean needContent) throws IOException {
        String TAG = "RxRetrofitLog";
        Log.d(TAG, "RxRetrofit > ============================================");
        // 时间
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        Log.d(TAG, "RxRetrofit > 耗时: " + duration + "毫秒");

        // 请求地址
        Request request = chain.request();
        Log.d(TAG, "RxRetrofit > 地址: " + request.url());

        // 参数
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

        // 结果
        if (needContent) {
            String content;
            try {
                content = response.body().string();
            } catch (Exception e) {
                content = "";
            }
            Log.d(TAG, "RxRetrofit > 结果: \n" + content);
        } else {
            Log.d(TAG, "RxRetrofit > 结果: 无需打印");
        }
        Log.d(TAG, "RxRetrofit > ============================================");
    }
}
