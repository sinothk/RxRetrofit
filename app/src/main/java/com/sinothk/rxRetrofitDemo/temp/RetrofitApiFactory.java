//package com.sinothk.rxRetrofitDemo.temp;
//
//import android.text.TextUtils;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.hy.jy.data.Constant;
//import com.hy.jy.data.cache.LoginInfoCache;
//import com.hy.jy.data.cache.PrefCache;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * 类描述：Api 工厂类
// * 创建人：毛华磊
// * 创建时间：2016/10/13 11:30
// * 修改人：
// * 修改时间：
// * 修改备注：
// */
//public class RetrofitApiFactory {
//    /**
//     * 服务器Api地址
//     */
//   public static final String BASE_URL = "http://58.16.181.23:48886/jygj-oa-webapp/";
////    public static final String BASE_URL = " http://114.55.40.72:8080/jygj-oa-webapp/";
////    public static final String BASE_URL = "http://192.168.1.23:8888/jygj-oa-webapp/";
////    public static final String BASE_URL = "http://192.168.1.25:48999/jygj-oa-webapp/";
////    public static final String BASE_URL = "http://192.168.1.35:9526/jygj-oa-webapp/";
////    public static final String BASE_URL = "http://192.168.1.28:8080/jygj-oa-webapp/"; // wang
////    public static final String BASE_URL = " http://192.168.1.253:8080/jygj-oa-webapp/"; // pan
////    private static final String BASE_URL = " http://192.168.1.43:8080/jygj-oa-webapp/";
////    private static final String BASE_URL = " http://192.168.1.62:8080/jygj-oa-webapp/";
//
//    /**
//     * 文件上传下载地址
//     */
//
////    public static final String DOWN_LOAD_URL = "http://114.55.40.72:8080/gyzkhy-upload-webapp/";
////   public static final String DOWN_LOAD_URL = "http://192.168.1.35:9526/gyzkhy-upload-webapp/";
////public static final String DOWN_LOAD_URL = "http://192.168.1.34:48998/gyzkhy-upload-webapp/";
//    public static final String DOWN_LOAD_URL = "http://58.16.181.23:48886/gyzkhy-upload-webapp/";
////        public static final String DOWN_LOAD_URL = "http://192.168.1.35:9526/gyzkhy-upload-webapp/";
////public static final String DOWN_LOAD_URL = "http://192.168.1.23:8888/gyzkhy-upload-webapp/";
////    public static final String DOWN_LOAD_URL = "http://192.168.1.251:8888/gyzkhy-upload-webapp/";
//    public static final String DOWN_LOAD_PATH = "file/downloadByPath.do?filePath=";
//
//    /**
//     * 获得文件
//     *
//     * @param imagePath
//     */
//    public static String getFileUrl(String imagePath) {
//        return RetrofitApiFactory.DOWN_LOAD_URL + RetrofitApiFactory.DOWN_LOAD_PATH + imagePath;
//    }
//
//
//
//    /**
//     * OkhttpClient
//     */
//    private static OkHttpClient mOkHttpClient;
//
//    /**
//     * 代理接口构建类
//     */
//    private static Retrofit retrofit;
//
//    /**
//     * Http连接超时
//     */
//    private static int CONNECT_TIMEOUT = 20;
//
//    /**
//     * Http 读取超时
//     */
//    private static int READ_TIMEOUT = 20;
//
//    /**
//     * Http 写入超时
//     */
//    private static int WRITE_TIMEOUT = 20;
//
//    static {
//        buildOkHttpClient();
//        buildRetrofit();
//    }
//
//    /**
//     * 构建 Retrofit
//     */
//    private static void buildRetrofit() {
//
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(mOkHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//对转换后的数据进行再包装
//                .build();
//
//    }
//
//    /**
//     * OkHttpClient 请求配置
//     */
//    private static void buildOkHttpClient() {
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
//                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
//                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
//                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
////        builder.addInterceptor(logging);
////        mOkHttpClient = builder.build();
//
//
//        //加入日志拦截器，请求头添加拦截器
//        mOkHttpClient = builder.build();
//
//        mOkHttpClient = builder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//                String role = (String) PrefCache.getData(Constant.LOGIN_ROLE, "");
//                String status = "";
//                if (TextUtils.equals(role, Constant.COMPANY_EMPLOYER) || TextUtils.equals(role, Constant.COMPANY_WORKER) || TextUtils.equals(role, Constant.MANAGER)) {
//                    status = "company";
//                }
//
//
//                Request request = original.newBuilder()
//                        .header("status", status)
//                        .method(original.method(), original.body())
//                        .build();
//
//                return chain.proceed(request);
//            }
//        }).addInterceptor(logging).build();
//
//        mOkHttpClient = builder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//                String sessionKey = "";
//
////                if (LoginInfoCache.get() != null&&!TextUtils.isEmpty(LoginInfoCache.get().getToken())) {
////                    sessionKey = LoginInfoCache.get().getToken();
////                }
//
//                Request request = original.newBuilder()
//                        .header("sessionKey", TextUtils.isEmpty(sessionKey)?"":sessionKey)
//                        .method(original.method(), original.body())
//                        .build();
//                return chain.proceed(request);
//            }
//        }).addInterceptor(logging).build();
//
//        mOkHttpClient = builder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//                String id = "";
//
////                if (LoginInfoCache.get() != null&&!TextUtils.isEmpty(LoginInfoCache.get().getUserId())) {
////                    id = LoginInfoCache.get().getUserId();
////                }
//
//                Request request = original.newBuilder()
//                        .header("userId", TextUtils.isEmpty(id)?"":id)
//                        .method(original.method(), original.body())
//                        .build();
//                return chain.proceed(request);
//            }
//        }).addInterceptor(logging).build();
//    }
//
//
//    /**
//     * 创建不同的Api 接口对象
//     *
//     * @param serviceClass
//     * @param <T>
//     * @return
//     */
//
//    public static <T> T createApi(Class<T> serviceClass) {
//
//        return retrofit.create(serviceClass);
//    }
//
//
//}
