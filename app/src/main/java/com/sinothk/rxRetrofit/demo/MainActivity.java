package com.sinothk.rxRetrofit.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.sinothk.rxRetrofit.demo.api.NearbyApi;
import com.sinothk.rxRetrofit.demo.bean.UserEntity;
import com.sinothk.rxRetrofitDemo.R;
import com.sinothk.rxretrofit.RetrofitFactory;
import com.sinothk.rxretrofit.bean.PageData;
import com.sinothk.rxretrofit.bean.ResultData;

import java.util.List;

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

//        RetrofitFactory.init("http://192.168.124.26:8888/").create(NearbyApi.class)
//                .findUsersByKeyword("38")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ResultData<List<UserEntity>>>() {
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
//                    public void onNext(ResultData<List<UserEntity>> resultData) {
//                        if (resultData != null) {
//                            List<UserEntity> userList = resultData.getData();
//
//                            if (userList != null && userList.size() > 0) {
//                                Log.e("onNext", userList.get(0).getEmail());
//                            } else {
//                                Log.e("onNext", "onError ... ");
//                            }
//                        }
//                    }
//                });
//        RetrofitFactory.init(BaseApi.baseUrl, BaseApi.getHeaderData())
//                .create(NearbyApi.class)
//                .findUsersByKeyword("38")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ResultData<List<UserEntity>>>() {
//                    @Override
//                    public void onStart() {
//                        Toast.makeText(MainActivity.this, "onStart", Toast.LENGTH_SHORT).show();
//                    }
//
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
//                    public void onNext(ResultData<List<UserEntity>> resultData) {
//                        if (resultData != null) {
//                            List<UserEntity> userList = resultData.getData();
//
//                            if (userList != null && userList.size() > 0) {
//                                Log.e("onNext", userList.get(0).getEmail());
//                            } else {
//                                Log.e("onNext", "onError ... ");
//                            }
//                        }
//                    }
//                });

        RetrofitFactory.init(BaseApi.baseUrl).create(NearbyApi.class)
                .findUsersByKeyword("38")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultData<PageData<List<UserEntity>>>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ResultData<PageData<List<UserEntity>>> resultData) {
                        if (resultData != null) {

                            if (resultData.getData() != null) {
                                Log.e("onNext", resultData.getData().isHaveNext() + "");
                                Log.e("onNext", resultData.getData().getPageIndex() + "");

                                PageData<List<UserEntity>> page = resultData.getData();

                                List<UserEntity> userList = page.getData();

                                for (UserEntity userEntity : userList) {
                                    Log.e("onNext", userEntity.getUserName());
                                }
                            }
                        }
                    }
                });
    }
}
