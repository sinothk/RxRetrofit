package com.sinothk.rxRetrofitDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.sinothk.rxRetrofitDemo.retrofit.NearbyApi;
import com.sinothk.rxRetrofitDemo.retrofit.RetrofitFactory;
import com.sinothk.rxRetrofitDemo.retrofit.temp.PageInfo;
import com.sinothk.rxRetrofitDemo.retrofit.temp.ResultData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        RetrofitFactory.init2()
//                .create(UserApi.class)
//                .weather("贵阳")
//                .enqueue(new Callback<RootData<PageInfo<UserBean>>>() {
//                    @Override
//                    public void onResponse(Call<RootData<PageInfo<UserBean>>> call, Response<RootData<PageInfo<UserBean>>> response) {
//                        RootData rootData = response.body();
//
//                        Toast.makeText(MainActivity.this, "" + rootData.getData().getForecast().size(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call<RootData<PageInfo<UserBean>>> call, Throwable t) {
//                        if (call == null) {
//
//                        }
//                    }
//                });

//        RetrofitFactory.init().create(UserApi.class)
//                .weather2("贵阳")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<RootData<PageInfo<UserBean>>>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onStart() {
//                        Toast.makeText(MainActivity.this, "onStart", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(RootData<PageInfo<UserBean>> rootData) {
//                        Toast.makeText(MainActivity.this, "" + rootData.getData().getForecast().size(), Toast.LENGTH_SHORT).show();
//                    }
//                });

//        String jsonStr = "{\"data\":{},\"pageNo\":0,\"pageSize\":0}";
//        RequestBody jsonBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonStr);
//

        RetrofitFactory.init().create(NearbyApi.class)
                .nearbyUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultData<PageInfo>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStart() {
                        Toast.makeText(MainActivity.this, "onStart", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ResultData<PageInfo> rootData) {

                        Log.e("rootData", "============ rootData ============" + rootData.getMsg());

//                        if (rootData == null) {
//
//                        }

//                        Toast.makeText(MainActivity.this, "" + rootData.getData().getForecast().size(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
