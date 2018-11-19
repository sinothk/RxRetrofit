package com.sinothk.rxretrofit.callback;

import android.app.Activity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author : 梁玉涛
 * e-mail : 381518188@qq.com
 * date   : 2018/11/19 14:56
 * desc   :
 */
public abstract class DownloadCallback implements Callback<ResponseBody> {

    // 监听器
    public abstract void onStart(); //运行在UI线程

    public abstract void onProgress(long currSize, long totalSize, int progress);//运行在UI线程

    public abstract void onFinish(String path);//运行在UI线程

    public abstract void onFail(String errorInfo);//运行在UI线程

    // ===============================================
    private Activity activity;
    private String filePath;

    public DownloadCallback(Activity mActivity, String path) {
        activity = mActivity;
        filePath = path;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        //将Response写入到从磁盘中，详见下面分析
        //注意，这个方法是运行在子线程中的
        writeResponseToDisk(activity, filePath, response, this);
    }

    @Override
    public void onFailure(Call<ResponseBody> call, final Throwable e) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onFail(e.getMessage());
            }
        });
    }


    private static void writeResponseToDisk(Activity activity, String path, Response<ResponseBody> response, DownloadCallback downloadCallback) {
        //从response获取输入流以及总大小
        assert response.body() != null;
        writeFileFromIS(activity, new File(path), response.body().byteStream(), response.body().contentLength(), downloadCallback);
    }

    //将输入流写入文件
    private static void writeFileFromIS(Activity activity, final File file, InputStream is, final long totalLength, final DownloadCallback downloadListener) {
        int sBufferSize = 8192;

        //开始下载
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                downloadListener.onStart();
            }
        });


        //创建文件
        if (!file.exists()) {
            if (!file.getParentFile().exists())
                file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloadListener.onFail("createNewFile IOException");
                    }
                });
            }
        }

        OutputStream os = null;
        long currentLength = 0;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte data[] = new byte[sBufferSize];
            int len;
            while ((len = is.read(data, 0, sBufferSize)) != -1) {
                os.write(data, 0, len);
                currentLength += len;

                final long currLength = currentLength;
                //计算当前下载进度
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloadListener.onProgress(currLength, totalLength, (int) (100 * currLength / totalLength));
                    }
                });
            }

            //下载完成，并返回保存的文件路径
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    downloadListener.onFinish(file.getAbsolutePath());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    downloadListener.onFail("IOException");
                }
            });

        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
