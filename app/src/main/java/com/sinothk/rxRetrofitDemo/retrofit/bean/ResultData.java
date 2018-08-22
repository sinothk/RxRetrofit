package com.sinothk.rxRetrofitDemo.retrofit.bean;

public class ResultData<T> {
    private static final int codeSuccess = 0;
    private static final int codeError = 1;

    private int code;
    private String msg;
    private T data;

    private ResultData(int code, String msg, T data) {
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

    public static ResultData getSuccess(String msg, Object data) {
        return new ResultData(codeSuccess, msg, data);
    }

    public static ResultData getError(String msg) {
        return new ResultData(codeError, msg, null);
    }
}
