package com.sinothk.rxRetrofitDemo.retrofit.bean;

import java.util.List;

/**
 * <pre>
 *  创建:  LiangYT 2018/6/23/023 on 1:27
 *  项目: RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public class DataBean {
    /**
     * shidu : 54%
     * pm25 : 48.0
     * pm10 : 86.0
     * quality : 良
     * wendu : 25
     * ganmao : 极少数敏感人群应减少户外活动
     * yesterday : {"date":"22日星期五","sunrise":"04:46","high":"高温 30.0℃","low":"低温 21.0℃","sunset":"19:46","aqi":60,"fx":"西南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}
     * forecast : [{"date":"23日星期六","sunrise":"04:46","high":"高温 35.0℃","low":"低温 24.0℃","sunset":"19:46","aqi":83,"fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"24日星期日","sunrise":"04:46","high":"高温 35.0℃","low":"低温 24.0℃","sunset":"19:47","aqi":93,"fx":"南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"25日星期一","sunrise":"04:47","high":"高温 34.0℃","low":"低温 24.0℃","sunset":"19:47","aqi":82,"fx":"北风","fl":"<3级","type":"雷阵雨","notice":"带好雨具，别在树下躲雨"},{"date":"26日星期二","sunrise":"04:47","high":"高温 35.0℃","low":"低温 24.0℃","sunset":"19:47","aqi":53,"fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"27日星期三","sunrise":"04:47","high":"高温 36.0℃","low":"低温 25.0℃","sunset":"19:47","aqi":63,"fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]
     */

    private String shidu;
    private double pm25;
    private double pm10;
    private String quality;
    private String wendu;
    private String ganmao;
    private YesterdayBean yesterday;
    private List<ForecastBean> forecast;

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public YesterdayBean getYesterday() {
        return yesterday;
    }

    public void setYesterday(YesterdayBean yesterday) {
        this.yesterday = yesterday;
    }

    public List<ForecastBean> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastBean> forecast) {
        this.forecast = forecast;
    }
}
