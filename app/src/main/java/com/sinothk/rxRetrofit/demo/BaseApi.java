package com.sinothk.rxRetrofit.demo;

import java.util.HashMap;

/**
 * <pre>
 *  创建:  LiangYT 2018/8/22/022 on 14:37
 *  项目: RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public class BaseApi {

    public static String baseUrl = "http://192.168.2.135:8888/";

    public static HashMap<String, String> getHeaderData() {
        HashMap<String, String> headerDataMap = new HashMap<>();
        headerDataMap.put("token", "112233445566778899");
        headerDataMap.put("userCode", "381518188");
        headerDataMap.put("userName", "LiangYT");
        return headerDataMap;
    }
}
