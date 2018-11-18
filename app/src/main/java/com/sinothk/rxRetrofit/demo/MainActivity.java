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

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // 分页：
//        RetrofitFactory.init(BaseApi.baseUrl).create(NearbyApi.class)
//                .findUsersByKeyword("38")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ResultData<PageData<List<UserEntity>>>>() {
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
//                    public void onNext(ResultData<PageData<List<UserEntity>>> resultData) {
////                        if (resultData != null) {
////
////                            if (resultData.getData() != null) {
////                                Log.e("onNext", resultData.getData().isHaveNext() + "");
////                                Log.e("onNext", resultData.getData().getPageIndex() + "");
////
////                                PageData<List<UserEntity>> page = resultData.getData();
////
////                                List<UserEntity> userList = page.getData();
////
////                                for (UserEntity userEntity : userList) {
////                                    Log.e("onNext", userEntity.getUserName());
////                                }
////                            }
////                        }
//                    }
//                });
//
//        RetrofitFactory.init(BaseApi.baseUrl, BaseApi.getHeaderData());
//
//        // 传实体
//        UserEntity user = new UserEntity();
//        user.setId(111);
//        user.setUserName("LiangYT");
//        RetrofitFactory.init(BaseApi.baseUrl).create(NearbyApi.class)
//                .updateUser(user)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ResultData<UserEntity>>() {
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
//                    public void onNext(ResultData<UserEntity> resultData) {
//                        if (resultData != null) {
//
////                            if (resultData.getData() != null) {
//////                                Log.e("onNext", resultData.getData().isHaveNext() + "");
//////                                Log.e("onNext", resultData.getData().getPageIndex() + "");
//////
//////                                PageData<List<UserEntity>> page = resultData.getData();
//////
//////                                List<UserEntity> userList = page.getData();
//////
//////                                for (UserEntity userEntity : userList) {
//////                                    Log.e("onNext", userEntity.getUserName());
//////                                }
////                            }
//                        }
//                    }
//                });


    }
}
