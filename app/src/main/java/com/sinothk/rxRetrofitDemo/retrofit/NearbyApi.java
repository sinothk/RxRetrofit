package com.sinothk.rxRetrofitDemo.retrofit;


import com.sinothk.rxRetrofitDemo.retrofit.bean.UserEntity;
import com.sinothk.rxRetrofitDemo.retrofit.temp.ResultData;

import java.util.List;

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

    @POST("ol/user/findUsersByKeyword")
    Observable<ResultData<List<UserEntity>>> findUsersByKeyword(@Query("keyword") String keyword); //@Body RequestBody jsonBody
}
