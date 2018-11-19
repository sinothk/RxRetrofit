package com.sinothk.rxretrofit;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author : 梁玉涛
 * e-mail : 381518188@qq.com
 * date   : 2018/11/1911:02
 * desc   :
 */
public class RequestFactory {

    public static MultipartBody.Part createFileParam(String fileKey, File file) {
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        return MultipartBody.Part.createFormData(fileKey, file.getName(), requestFile);
        // 后台接收：
        // updateUserAvatar(@RequestParam("userCode") String userCode, @RequestParam("file") MultipartFile avatarFile)
    }
}
