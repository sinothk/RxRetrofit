package com.sinothk.rxRetrofitDemo.retrofit;


import com.sinothk.rxRetrofitDemo.retrofit.temp.PageInfo;
import com.sinothk.rxRetrofitDemo.retrofit.temp.ResultData;
import com.sinothk.rxRetrofitDemo.retrofit.temp.UserEntity;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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

    @POST("nearby/nearbyUserList")
    Observable<ResultData<PageInfo>> nearbyUserList();//@Body RequestBody jsonBody


}
