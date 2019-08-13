package com.sinothk.rxretrofit.interceptor;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/26 on 11:26
 *  项目:  RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public class LogHeaderInterceptor implements Interceptor {

    private HashMap<String, String> headerMap;
    private boolean needResult, isPrintLog;

    public LogHeaderInterceptor(boolean needResult, HashMap<String, String> headerMap, boolean isPrintLog) {
        this.headerMap = headerMap;
        this.needResult = needResult;
        this.isPrintLog = isPrintLog;
    }

    public LogHeaderInterceptor(boolean needResult, boolean isPrintLog) {
        this.needResult = needResult;
        this.isPrintLog = isPrintLog;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        if (headerMap != null) {
            requestBuilder.headers(Headers.of(headerMap));
        }
        Request signedRequest = requestBuilder.build();

        // 请求响应时间
        long t1 = System.currentTimeMillis();
        Response response = chain.proceed(signedRequest);
        long t2 = System.currentTimeMillis();
        double time = t2 - t1;

        // 请求结果
        MediaType contentType = null;
        String bodyString = "";
        if (response.body() != null) {
            contentType = response.body().contentType();
            bodyString = response.body().string();
        }
        // 打印信息
        printInfo(signedRequest, headerMap, request, time, bodyString);

        if (response.body() != null) {
            // 深坑！
            // 打印body后原ResponseBody会被清空，需要重新设置body
            ResponseBody body = ResponseBody.create(contentType, bodyString);
            return response.newBuilder().body(body).build();
        } else {
            return response;
        }
    }

    private void printInfo(final Request signedRequest, final HashMap<String, String> headerMap, final Request request, final double time, final String responseBodyStr) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isPrintLog) {

                    String TAG = "RxRetrofitLog";
                    Log.d(TAG, "RxRetrofit > =====================开始=======================");
                    try {
                        // url
                        String url = signedRequest.url().toString();
                        Log.d(TAG, "RxRetrofit > 地址: " + url);

                        // 请求方式
                        String requestType = request.method();
                        Log.d(TAG, "RxRetrofit > 方式: " + requestType);

                        if (headerMap != null) {
                            Log.d(TAG, "RxRetrofit > 头部: " + headerMap.toString());
                        }

                        // 请求信息
                        Log.d(TAG, "RxRetrofit > 参数：");
                        Set<String> setList = request.url().queryParameterNames();
                        for (String paramName : setList) {
                            String value = request.url().queryParameterValues(paramName).get(0);
                            Log.d(TAG, "RxRetrofit >     " + paramName + " => " + value);
                        }

                        Log.d(TAG, "RxRetrofit > 耗时: " + time + "毫秒");
                        if (needResult) {
                            if (responseBodyStr.length() > 3600) {
                                StringBuilder infoStr = new StringBuilder("RxRetrofit > 返回: ↓\n");
                                for (int i = 0; i < responseBodyStr.length(); i += 3600) {
                                    if (i + 3600 < responseBodyStr.length()) {
                                        infoStr.append("\n").append(responseBodyStr.substring(i, i + 3600));

                                    } else {
                                        infoStr.append("\n").append(responseBodyStr.substring(i));
                                    }
                                }
                                Log.d(TAG, infoStr.toString());
                            } else {
                                Log.d(TAG, "RxRetrofit > 返回: ↓\n" + responseBodyStr);
                            }
                        } else {
                            Log.d(TAG, "RxRetrofit > 返回: 无需打印！");
                        }
                    } catch (Exception e) {
                        Log.d(TAG, "RxRetrofit > 报错\n" + e.getMessage());
                    }
                    Log.d(TAG, "RxRetrofit > =====================结束=======================");
                }
            }
        }).start();
    }
}
