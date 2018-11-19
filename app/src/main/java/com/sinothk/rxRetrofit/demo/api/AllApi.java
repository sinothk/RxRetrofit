package com.sinothk.rxRetrofit.demo.api;


import com.sinothk.rxRetrofit.demo.bean.UserEntity;
import com.sinothk.rxretrofit.bean.PageData;
import com.sinothk.rxretrofit.bean.ResultData;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Completable;
import rx.Observable;

/**
 * <pre>
 *  创建:  LiangYT 2018/6/23/023 on 0:41
 *  项目: RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public interface AllApi {

//    @Headers("apikey:b86c2269fe6588bbe3b41924bb2f2da2")
//    @GET("open/api/weather/json.shtml")
//    Call<RootData<PageInfo<UserBean>>> weather(@Query("city") String cityName);

//    @POST("ol/user/findUsersByKeyword")
//    Observable<ResultData<List<UserEntity>>> findUsersByKeyword(@Query("keyword") String keyword); //@Body RequestBody jsonBody


    @POST("slogan/user/findUsersByKeyword")
    Observable<ResultData<PageData<List<UserEntity>>>> findUsersByKeyword(@Query("keyword") String keyword);

    @POST("slogan/user/updateUser")
    Observable<ResultData<UserEntity>> updateUser(@Body UserEntity user);

    @Multipart
    @POST("slogan/user/updateUserAvatar")
    Observable<ResultData<UserEntity>> uploadFile(@Query("userCode") String userCode, @Part MultipartBody.Part file);

    @Multipart
    @POST("slogan/user/sendDaily")
    Observable<ResultData<UserEntity>> sendDaily(@Query("userCode") String userCode,  @PartMap() Map<String, RequestBody> maps);


    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url);
}