package com.sinothk.rxRetrofitDemo.retrofit.bean;

import java.util.Date;

public class UserEntity {

    private Integer id;
    private String userCode; // 编号
    private String userName; // 姓名
    private String nickname; // 昵称
    private String remarkName; // 备注
    private String idNo; // 身份证ID

    private String userPwd; // 密码

    private String phoneNum; // 手机号
    private String email; // 邮箱

    //
    private Integer sex; // 性别
    private Date birthday; // 生日
    private String hometown; // 家乡
    private String photos; // 相册
    private Integer emotion;//情感
    private String daily; // 日常
    private String userDescribe; // 描述
    //
    private Date createDate; // 注册时间
    private Date loginDate;  // 登录时间

    private Integer relationship; // 关系

    public UserEntity() {
    }

    public UserEntity(String email) {
        this.email = email;
    }

    public UserEntity(String userCode, String email, String userPwd) {
        this.userCode = userCode;
        this.email = email;
        this.userPwd = userPwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public Integer getEmotion() {
        return emotion;
    }

    public void setEmotion(Integer emotion) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getRelationship() {
        return relationship;
    }

    public void setRelationship(Integer relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", remarkName='" + remarkName + '\'' +
                ", idNo='" + idNo + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", hometown='" + hometown + '\'' +
                ", photos='" + photos + '\'' +
                ", emotion=" + emotion +
                ", daily='" + daily + '\'' +
                ", userDescribe='" + userDescribe + '\'' +
                ", createDate=" + createDate +
                ", loginDate=" + loginDate +
                ", relationship=" + relationship +
                '}';
    }
}
