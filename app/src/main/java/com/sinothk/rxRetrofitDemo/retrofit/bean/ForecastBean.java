package com.sinothk.rxRetrofitDemo.retrofit.bean;

/**
 * <pre>
 *  创建:  LiangYT 2018/6/23/023 on 1:29
 *  项目: RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public class ForecastBean {
    /**
     * date : 23日星期六
     * sunrise : 04:46
     * high : 高温 35.0℃
     * low : 低温 24.0℃
     * sunset : 19:46
     * aqi : 83.0
     * fx : 南风
     * fl : <3级
     * type : 多云
     * notice : 阴晴之间，谨防紫外线侵扰
     */

    private String date;
    private String sunrise;
    private String high;
    private String low;
    private String sunset;
    private double aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public double getAqi() {
        return aqi;
    }

    public void setAqi(double aqi) {
        this.aqi = aqi;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}