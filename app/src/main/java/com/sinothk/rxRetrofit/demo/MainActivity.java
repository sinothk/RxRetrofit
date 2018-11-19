package com.sinothk.rxRetrofit.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.sinothk.rxRetrofit.demo.api.AllApi;
import com.sinothk.rxRetrofit.demo.bean.UserEntity;
import com.sinothk.rxretrofit.param.RetrofitParam;
import com.sinothk.rxretrofit.bean.PageData;
import com.sinothk.rxretrofit.bean.ResultData;
import com.sinothk.rxretrofit.callback.DownloadCallback;
import com.sinothk.rxRetrofitDemo.R;
import com.sinothk.rxretrofit.RetrofitFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // 分页：
//        RetrofitFactory.init(BaseApi.baseUrl).create(AllApi.class)
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
//        RetrofitFactory.init(BaseApi.baseUrl).create(AllApi.class)
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
//
//
//        // 传单文件文件和键值对
//        File file = new File("/storage/emulated/0/Download/wKgANVvEPSeASGEFAAQ7wQP8jK4342.png");
//        RetrofitFactory.init(BaseApi.baseUrl).create(AllApi.class)
//                .uploadFile("381518188", RetrofitParam.createFileParam("file", file))
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
//                        }
//                    }
//                });
//
//        // 传单文件文件和实体
//        File file = new File("/storage/emulated/0/Download/wKgANVvEPSeASGEFAAQ7wQP8jK4342.png");
//        ArrayList<File> files = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            files.add(file);
//        }
//
//        RetrofitFactory.init(BaseApi.baseUrl).create(AllApi.class)
//                .sendDaily("381518188", RetrofitParam.createFileListParam("file", files))
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
//                        }
//                    }
//                });

        // 文件下载
        String url = "c9b68ccfd26029de9818e781690317bc.apk?attname=mgdj-release_2.6.4_20_1117.apk&sign=59f2315acffb0a98ee97926cca3b1d2b&t=5bf27172";
        String path = "/storage/emulated/0/Download/123456789.apk";

        RetrofitFactory
                .init("http://app-global.pgyer.com/", Executors.newSingleThreadExecutor())
                .create(AllApi.class)
                .download(url)
                .enqueue(new DownloadCallback(this, path) {
                    @Override
                    public void onStart() {
                        //运行在子线程
                        Log.e("DownloadUtil", "onStart");
                        toastUtil("onStart");
                    }

                    @Override
                    public void onProgress(long currSize, long totalSize, int progress) {
                        Log.e("DownloadUtil", "progress = " + progress + ", currSize=" + currSize + ", totalSize=" + totalSize);
                    }

                    @Override
                    public void onFinish(String path) {
                        //运行在子线程
                        Log.e("DownloadUtil", "onFinish：path = " + path);

                        toastUtil("progress = " + path);
                    }

                    @Override
                    public void onFail(String errorInfo) {
                        //运行在子线程
                        Log.e("DownloadUtil", "onFail：errorInfo = " + errorInfo);
                        toastUtil("progress = " + errorInfo);
                    }
                });
    }

    private void toastUtil(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
