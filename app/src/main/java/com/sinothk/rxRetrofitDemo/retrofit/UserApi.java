package com.sinothk.rxRetrofitDemo.retrofit;


import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
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
public interface UserApi {

//    @Headers("apikey:b86c2269fe6588bbe3b41924bb2f2da2")
//    @GET("open/api/weather/json.shtml")
//    Call<RootData<PageInfo<UserBean>>> weather(@Query("city") String cityName);

    @Headers({"_user_id:42467358817", "token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI0NWFqNjMxNCIsImFwcE5hbWUiOiLnp7vliqjnu4jnq6_lubPlj7AiLCJleHAiOjE1Mjk4MTI4MDB9.k9I7beap7GioNxDPFh7uxL-w4yLBCAQdD_iqPhM8Uy5RBbB1MfAjspXGGl0giohbokvnIUgDtb5s9Mj56NGeQDKMwW6nPVaTg6AWfn05wrQcPjRqsahQ-Hp6wrTaquKRaaVrbtHGrjz8GIlYHtAS_sRnvfbUAMDv5WJS_WUV6ig"})
    @POST("api/mt/appmanager/find")
    Observable<ResultObj<PageInfos<ApplicationDTO>>> weather2(@Body RequestBody jsonBody);


}
