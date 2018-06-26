package com.sinothk.rxRetrofitDemo.retrofit.bean;

/**
 * <pre>
 *  创建:  LiangYT 2018/6/23/023 on 0:43
 *  项目: RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public class RootData<T> {

    /**
     * date : 20180623
     * message : Success !
     * status : 200
     * city : 北京
     * count : 1
     * data : {"shidu":"54%","pm25":48,"pm10":86,"quality":"良","wendu":"25","ganmao":"极少数敏感人群应减少户外活动","yesterday":{"date":"22日星期五","sunrise":"04:46","high":"高温 30.0℃","low":"低温 21.0℃","sunset":"19:46","aqi":60,"fx":"西南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},"forecast":[{"date":"23日星期六","sunrise":"04:46","high":"高温 35.0℃","low":"低温 24.0℃","sunset":"19:46","aqi":83,"fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"24日星期日","sunrise":"04:46","high":"高温 35.0℃","low":"低温 24.0℃","sunset":"19:47","aqi":93,"fx":"南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"25日星期一","sunrise":"04:47","high":"高温 34.0℃","low":"低温 24.0℃","sunset":"19:47","aqi":82,"fx":"北风","fl":"<3级","type":"雷阵雨","notice":"带好雨具，别在树下躲雨"},{"date":"26日星期二","sunrise":"04:47","high":"高温 35.0℃","low":"低温 24.0℃","sunset":"19:47","aqi":53,"fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"27日星期三","sunrise":"04:47","high":"高温 36.0℃","low":"低温 25.0℃","sunset":"19:47","aqi":63,"fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]}
     */


    private T test;

    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private DataBean data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public T getTest() {
        return test;
    }

    public void setTest(T test) {
        this.test = test;
    }
}
