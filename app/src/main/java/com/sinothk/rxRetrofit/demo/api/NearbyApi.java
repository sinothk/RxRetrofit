package com.sinothk.rxRetrofit.demo.api;


import com.sinothk.rxRetrofit.demo.bean.UserEntity;
import com.sinothk.rxretrofit.bean.PageData;
import com.sinothk.rxretrofit.bean.ResultData;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * <pre>
 *  创建:  LiangYT 2018/6/23/023 on 0:41
 *  项目: RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public interface NearbyApi {

//    @Headers("apikey:b86c2269fe6588bbe3b41924bb2f2da2")
//    @GET("open/api/weather/json.shtml")
//    Call<RootData<PageInfo<UserBean>>> weather(@Query("city") String cityName);

//    @POST("ol/user/findUsersByKeyword")
//    Observable<ResultData<List<UserEntity>>> findUsersByKeyword(@Query("keyword") String keyword); //@Body RequestBody jsonBody


    @POST("slogan/user/findUsersByKeyword")
    Observable<ResultData<PageData<List<UserEntity>>>> findUsersByKeyword(@Query("keyword") String keyword);

    @POST("slogan/user/updateUser")
    Observable<ResultData<UserEntity>> updateUser(@Body UserEntity user);
}
