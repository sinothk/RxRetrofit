package com.sinothk.rxretrofit.temp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sinothk.rxretrofit.RetrofitFactory;
import com.sinothk.rxretrofit.bean.PageData;
import com.sinothk.rxretrofit.bean.ResultData;
import com.sinothk.rxretrofit.callback.DownloadCallback;
import com.sinothk.rxretrofit.param.RetrofitParam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
