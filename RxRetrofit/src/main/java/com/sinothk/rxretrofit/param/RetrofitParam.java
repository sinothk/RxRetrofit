package com.sinothk.rxretrofit.param;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author : 梁玉涛
 * e-mail : 381518188@qq.com
 * date   : 2018/11/1911:02
 * desc   :
 */
public class RetrofitParam {

    /**
     * 创建单文件参数
     *
     * @param fileKey 和后台约定相同
     * @param file    文件列表
     * @return
     */
    public static MultipartBody.Part createFileParam(String fileKey, File file) {
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        return MultipartBody.Part.createFormData(fileKey, file.getName(), requestFile);

        //API:
        //    @Multipart
        //    @POST("slogan/user/updateUserAvatar")
        //    Observable<ResultData<UserEntity>> uploadFile(@Query("userCode") String userCode, @Part MultipartBody.Part file);
        // 后台接收：
        // updateUserAvatar(@RequestParam("userCode") String userCode, @RequestParam("file") MultipartFile avatarFile)
    }

    /**
     * 创建多文件参数
     *
     * @param fileKey 和后台约定相同
     * @param files   文件列表
     * @return
     */
    public static Map<String, RequestBody> createFileListParam(String fileKey, ArrayList<File> files) {
        Map<String, RequestBody> requestMap = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            requestMap.put(fileKey + "\"; filename=\"" + file.getName(), requestFile);
        }
        return requestMap;
        // API:
        //    @Multipart
        //    @POST("slogan/user/sendDaily")
        //    Observable<ResultData<UserEntity>> sendDaily(@Query("userCode") String userCode,  @PartMap() Map<String, RequestBody> maps);
        // 后台： RequestParam("fileKey") MultipartFile[] files
    }
}
