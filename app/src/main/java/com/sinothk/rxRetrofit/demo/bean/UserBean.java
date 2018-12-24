package com.sinothk.rxRetrofit.demo.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/24 on 23:06
 *  项目:  RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public class UserBean {

    /**
     * id : 1111
     * userCode : null
     * userName : null
     * userAvatar : null
     * nickname : LiangYT
     * remarkName : null
     * idNo : null
     * userPwd : null
     * phoneNum : null
     * email : null
     * sex : null
     * birthday : 0
     * hometown : null
     * photos : null
     * emotion : null
     * daily : null
     * userDescribe : null
     * createDate : 0
     * loginDate : 0
     * relationship : null
     * token : null
     * userLng : 0
     * userLat : 0
     * userCity : null
     * userProvince : null
     * userCountry : null
     */

    private String id;
    private String nickname;
    private int birthday;
    private String hometown;
    private String photos;
    private String emotion;
    private String daily;
    private String userDescribe;
    private int createDate;
    private int loginDate;
    private String relationship;
    private String token;
    private int userLng;
    private int userLat;
    private String userCity;
    private String userProvince;
    private String userCountry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }

    public String getUserDescribe() {
        return userDescribe;
    }

    public void setUserDescribe(String userDescribe) {
        this.userDescribe = userDescribe;
    }

    public int getCreateDate() {
        return createDate;
    }

    public void setCreateDate(int createDate) {
        this.createDate = createDate;
    }

    public int getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(int loginDate) {
        this.loginDate = loginDate;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserLng() {
        return userLng;
    }

    public void setUserLng(int userLng) {
        this.userLng = userLng;
    }

    public int getUserLat() {
        return userLat;
    }

    public void setUserLat(int userLat) {
        this.userLat = userLat;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }
}
