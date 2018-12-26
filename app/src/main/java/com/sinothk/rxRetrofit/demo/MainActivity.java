package com.sinothk.rxRetrofit.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sinothk.rxRetrofit.demo.api.AllApi;
import com.sinothk.rxRetrofit.demo.bean.UserBean;
import com.sinothk.rxRetrofit.demo.bean.UserEntity;
import com.sinothk.rxretrofit.RxRetrofit;
import com.sinothk.rxretrofit.param.RetrofitParam;
import com.sinothk.rxretrofit.bean.PageData;
import com.sinothk.rxretrofit.bean.ResultData;
import com.sinothk.rxretrofit.callback.DownloadCallback;
import com.sinothk.rxRetrofitDemo.R;
import com.sinothk.rxretrofit.RetrofitFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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

        findViewById(R.id.findPageDataBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 分页：
                RxRetrofit.init(BaseApi.baseUrl).create(AllApi.class)
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
                                }
                            }
                        });
            }
        });

        findViewById(R.id.submitEntityBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 传实体
                UserEntity user = new UserEntity();
                user.setUserCode("18111316175541");
                user.setUserName("SINOTHK");

                RxRetrofit.init(BaseApi.baseUrl).create(AllApi.class)
                        .updateUser(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ResultData<UserEntity>>() {
                            @Override
                            public void onCompleted() {
                                Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(ResultData<UserEntity> resultData) {
                                if (resultData != null) {
                                }
                            }
                        });
            }
        });

        findViewById(R.id.singleFileBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 传单文件文件和键值对
                File file = new File("/storage/emulated/0/Download/wKgANVvEPSeASGEFAAQ7wQP8jK4342.png");
                RxRetrofit.init(BaseApi.baseUrl).create(AllApi.class)
                        .uploadFile("381518188", RetrofitParam.createFileParam("file", file))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ResultData<UserEntity>>() {
                            @Override
                            public void onCompleted() {
                                Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(ResultData<UserEntity> resultData) {
                                if (resultData != null) {
                                }
                            }
                        });

            }
        });

        findViewById(R.id.multiFileBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 传多文件和实体
                File file = new File("/storage/emulated/0/Download/wKgANVvEPSeASGEFAAQ7wQP8jK4342.png");
                ArrayList<File> files = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    files.add(file);
                }

                RxRetrofit.init(BaseApi.baseUrl).create(AllApi.class)
                        .sendDaily("381518188", RetrofitParam.createFileListParam("file", files))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ResultData<UserEntity>>() {
                            @Override
                            public void onCompleted() {
                                Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(ResultData<UserEntity> resultData) {
                                if (resultData != null) {
                                }
                            }
                        });
            }
        });


        findViewById(R.id.fileDownBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 文件下载
                String url = "933cf91f8f45c1342d472bb0ef757ebc.apk?attname=MGDJ_v2.8.3_30_release_2018-12-10.apk&sign=a5fe3ab1ae0710475a8ded7f2fbc8bdc&t=5c231fc6";
                String path = "/storage/emulated/0/Download/21212.apk";

                RxRetrofit
                        .init("http://app-global.pgyer.com/", Executors.newSingleThreadExecutor())
                        .create(AllApi.class)
                        .download(url)
                        .enqueue(new DownloadCallback(MainActivity.this, path) {
                            @Override
                            public void onStart() {
                                Log.e("DownloadUtil", "onStart");
                                toastUtil("onStart");
                            }

                            @Override
                            public void onProgress(long currSize, long totalSize, int progress) {
                                Log.e("DownloadUtil", "progress = " + progress + ", currSize=" + currSize + ", totalSize=" + totalSize);

                                if (currSize == totalSize) {
                                    toastUtil("OK");
                                }
                            }

                            @Override
                            public void onFinish(String path) {
                                Log.e("DownloadUtil", "onFinish：path = " + path);

                                toastUtil("progress = " + path);
                            }

                            @Override
                            public void onFail(String errorInfo) {
                                Log.e("DownloadUtil", "onFail：errorInfo = " + errorInfo);
                                toastUtil("progress = " + errorInfo);
                            }
                        });
            }
        });

        findViewById(R.id.findPageDataBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxRetrofit.init("http://192.168.124.33:9999/").create(AllApi.class)
                        .login("381518188")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ResultData<UserBean>>() {
                            @Override
                            public void onCompleted() {
                                Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(ResultData<UserBean> resultData) {
                                if (resultData != null) {
                                }
                            }
                        });
            }
        });

        findViewById(R.id.addHeardBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put("h1", "111");
                map.put("h2", "222");

                RxRetrofit.init("http://192.168.124.33:9999/", map).create(AllApi.class)
                        .findUserByUserCode("中国")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ResultData<UserBean>>() {
                            @Override
                            public void onCompleted() {
                                Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(ResultData<UserBean> resultData) {
                                if (resultData != null) {
                                }
                            }
                        });
            }
        });
    }

    private void toastUtil(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
