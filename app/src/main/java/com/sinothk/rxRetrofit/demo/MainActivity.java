package com.sinothk.rxRetrofit.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

        findViewById(R.id.findPageDataBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 分页：
                RetrofitFactory.init(BaseApi.baseUrl).create(AllApi.class)
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
                RetrofitFactory.init(BaseApi.baseUrl).create(AllApi.class)
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
                RetrofitFactory.init(BaseApi.baseUrl).create(AllApi.class)
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
                // 传单文件文件和实体
                File file = new File("/storage/emulated/0/Download/wKgANVvEPSeASGEFAAQ7wQP8jK4342.png");
                ArrayList<File> files = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    files.add(file);
                }

                RetrofitFactory.init(BaseApi.baseUrl).create(AllApi.class)
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
                String url = "c72c378e0a5d827ebd94d2c880da01ec.apk?attname=mgdj-release_2.6.3_19_1112.apk&sign=c64d18d5db3ee659c5962e9e3a52c643&t=5bf2783d";
                String path = "/storage/emulated/0/Download/21212.apk";

                RetrofitFactory
                        .init("http://app-global.pgyer.com/", Executors.newSingleThreadExecutor())
                        .create(AllApi.class)
                        .download(url)
                        .enqueue(new DownloadCallback(MainActivity.this, path) {
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
        });
    }

    private void toastUtil(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
