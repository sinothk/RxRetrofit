package com.sinothk.rxretrofit.interceptor;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
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
    private boolean needResult;

    public LogHeaderInterceptor(HashMap<String, String> headerMap, boolean needResult) {
        this.headerMap = headerMap;
        this.needResult = needResult;
    }

    public LogHeaderInterceptor(boolean needResult) {
        this.needResult = needResult;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        if (headerMap != null) {
            requestBuilder.headers(Headers.of(headerMap));
        }
        Request signedRequest = requestBuilder.build();

        // url
        String url = signedRequest.url().toString();
        // 请求方式
        String requestType = request.method();
        // 请求信息

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
        printInfo(url, requestType, headerMap, signedRequest.body(), time, bodyString, needResult);

//        switch (request.method()) {
//            case "GET":
//                Log.d("retrofit-->",
//                        String.format("GET "
////                                                    + F_REQUEST_WITHOUT_BODY + F_RESPONSE_WITH_BODY,
////                                            signedRequest.url(),
////                                            time,
////                                            signedRequest.headers(),
////                                            response.code(),
////                                            response.headers(),
////                                            stringifyResponseBody(bodyString))
//                        ));
//                break;
//            case "POST":
//
//                Log.d("retrofit-->", "POST");
//                Log.d("retrofit-->", "url = " + signedRequest.url());
//                Log.d("retrofit-->", "time = " + time);
//                Log.d("retrofit-->", "headers = " + signedRequest.headers());
//
////                            Log.d("retrofit-->", "Request = " + stringifyRequestBody(signedRequest));
//                Log.d("retrofit-->", "bodyString = " + bodyString);
//
////                            Log.d("retrofit-->",
////                                    String.format("POST "
//////                                                    + F_REQUEST_WITH_BODY + F_RESPONSE_WITH_BODY,
////                                            signedRequest.url(),
////                                            time,
////                                            signedRequest.headers(),
////                                            stringifyRequestBody(signedRequest),
////                                            response.code(),
////                                            response.headers(),
////                                            stringifyResponseBody(bodyString))
////                                    ));
//                break;
//            case "PUT":
//                Log.d("retrofit-->",
//                        String.format("PUT "
////                                                    + F_REQUEST_WITH_BODY + F_RESPONSE_WITH_BODY,
////                                            signedRequest.url(),
////                                            time,
////                                            signedRequest.headers(),
////                                            signedRequest.body().toString(),
////                                            response.code(),
////                                            response.headers(),
////                                            stringifyResponseBody(bodyString)
//                        ));
//                break;
//            case "DELETE":
//                Log.d("retrofit-->",
//                        String.format("DELETE "
////                                                    + F_REQUEST_WITHOUT_BODY + F_RESPONSE_WITHOUT_BODY,
////                                            signedRequest.url(),
////                                            time,
////                                            signedRequest.headers(),
////                                            response.code(),
////                                            response.headers())
//                        ));
//                break;
//        }

        if (response.body() != null) {
            // 深坑！
            // 打印body后原ResponseBody会被清空，需要重新设置body
            ResponseBody body = ResponseBody.create(contentType, bodyString);
            return response.newBuilder().body(body).build();
        } else {
            return response;
        }
    }

    private void printInfo(String url, String requestType, HashMap<String, String> headerMap, RequestBody requestBody, double time, String responseBodyStr, boolean needResult) {
        String TAG = "RxRetrofitLog";
        Log.d(TAG, "RxRetrofit > =====================开始=======================");
        Log.d(TAG, "RxRetrofit > 地址: " + url);
        Log.d(TAG, "RxRetrofit > 方式: " + requestType);
        if (headerMap != null) {
            Log.d(TAG, "RxRetrofit > 头部: " + headerMap.toString());
        }
        Log.d(TAG, "RxRetrofit > 耗时: " + time + "毫秒");
        if (needResult) {
            Log.d(TAG, "RxRetrofit > 返回: ↓\n" + responseBodyStr);
        } else {
            Log.d(TAG, "RxRetrofit > 返回: 无需打印！");
        }
        Log.d(TAG, "RxRetrofit > =====================结束=======================");
    }
}
