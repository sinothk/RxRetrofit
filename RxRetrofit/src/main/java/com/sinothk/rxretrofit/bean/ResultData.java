package com.sinothk.rxretrofit.bean;

public class ResultData<T> {

    public static int SUCCESS = 200;
    public static int TOKEN_OVERDUE = 100;

    private int code;
    private String msg;
    private T data;

    public ResultData() {
    }

    public ResultData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
