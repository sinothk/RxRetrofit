package com.sinothk.rxretrofit;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.sinothk.rxretrofit.converterFactorys.FastJsonConverterFactory;
import com.sinothk.rxretrofit.interceptor.DownLoadInterceptor;
import com.sinothk.rxretrofit.interceptor.LogHeaderInterceptor;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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

                .addInterceptor(new LogHeaderInterceptor(true, true)).build();

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
                    .addInterceptor(new LogHeaderInterceptor(true, headerMap, true));

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
     * 在子线程中运行,文件下载
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

//    /**
//     * 无请求头请求 + 日志打印控制
//     *
//     * @param baseUrl
//     * @return
//     */
//    public static Retrofit initsssss(String baseUrl, boolean isPrintLog) {
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .readTimeout(2, TimeUnit.MINUTES)
//                .writeTimeout(2, TimeUnit.MINUTES)
//                .retryOnConnectionFailure(false)
//                .addInterceptor(new LogHeaderInterceptor(true, isPrintLog)
//                ).build();
//
//        // 创建网络请求接口的实例
//        return new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(new Retrofit2ConverterFactory())
//                .baseUrl(baseUrl)
//                .client(okHttpClient)//此client是为了打印信息
//                .build();
//    }

//    /**
//     * 带请求头请求 + 日志打印控制
//     *
//     * @param baseUrl   服务器地址
//     * @param headerMap 请求头键值对
//     * @return
//     */
//    @Deprecated
//    public static Retrofit init(String baseUrl, final HashMap<String, String> headerMap, boolean isPrintLog) {
//        if (headerMap == null || headerMap.isEmpty()) {
//            return init(baseUrl, isPrintLog);
//        } else {
//            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
//                    .addInterceptor(new LogHeaderInterceptor(true, headerMap, isPrintLog));
//
//            httpClient.connectTimeout(15, TimeUnit.SECONDS)
//                    .readTimeout(2, TimeUnit.MINUTES)
//                    .writeTimeout(2, TimeUnit.MINUTES);
//
//            OkHttpClient okHttpClient = httpClient.build();
//
//            return new Retrofit.Builder()
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .addConverterFactory(new Retrofit2ConverterFactory())//FastJsonConverterFactory.create() GsonConverterFactory
//                    .baseUrl(baseUrl)
//                    .client(okHttpClient)
//                    .build();
//        }
//    }

    public static Retrofit init4https(String baseUrl) {

        final X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        };

        final TrustManager[] trustAllCerts = new TrustManager[]{x509TrustManager};

        try {
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .retryOnConnectionFailure(false)
                    .sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .addInterceptor(new LogHeaderInterceptor(true, true)).build();

            // 创建网络请求接口的实例
            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(new Retrofit2ConverterFactory())
                    .baseUrl(baseUrl)
                    .client(okHttpClient)//此client是为了打印信息
                    .build();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 带请求头请求
     *
     * @param baseUrl   服务器地址
     * @param headerMap 请求头键值对
     * @return
     */
    public static Retrofit init4https(String baseUrl, final HashMap<String, String> headerMap) {

        if (headerMap == null || headerMap.isEmpty()) {
            return init4https(baseUrl);
        } else {

            final X509TrustManager x509TrustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            };

            final TrustManager[] trustAllCerts = new TrustManager[]{x509TrustManager};

            try {
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                        .addInterceptor(new LogHeaderInterceptor(true, headerMap, true));

                httpClient.connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(2, TimeUnit.MINUTES)
                        .writeTimeout(2, TimeUnit.MINUTES);

                OkHttpClient okHttpClient = httpClient
                        .sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager)
                        .hostnameVerifier(new HostnameVerifier() {
                            @Override
                            public boolean verify(String hostname, SSLSession session) {
                                return true;
                            }
                        })
                        .build();

                return new Retrofit.Builder()
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(new Retrofit2ConverterFactory())//FastJsonConverterFactory.create() GsonConverterFactory
                        .baseUrl(baseUrl)
                        .client(okHttpClient)
                        .build();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    /**
     * 在子线程中运行,文件下载
     *
     * @param baseUrl
     * @param executorService
     * @return
     */
    public static Retrofit init4https(String baseUrl, ExecutorService executorService) {

        final X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        };

        final TrustManager[] trustAllCerts = new TrustManager[]{x509TrustManager};

        try {
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .retryOnConnectionFailure(false)
                    .sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .addInterceptor(new DownLoadInterceptor()).build();

            // 创建网络请求接口的实例
            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(FastJsonConverterFactory.create()) //GsonConverterFactory
                    .baseUrl(baseUrl)
                    .client(okHttpClient)//此client是为了打印信息
                    .callbackExecutor(executorService)
                    .build();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static class Code {
        public static int SUCCESS = 0;

        public static int TOKEN_OVERDUE = 100;
    }
}



