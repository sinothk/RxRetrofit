package com.sinothk.rxRetrofitDemo.retrofit;

/**
 * <pre>
 *  创建:  LiangYT 2018/6/20/020 on 9:40
 *  项目: gxqptAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class ResultObj<T> {

    /**
     * errcode : 0
     * data : {"id":"42467358817","account":"zsyfj_tl","name":"谈亮","sex":"男","birthday":null,"nation":"汉族","photo":"","officetel":"","phone":"","workDesc":null,"status":null,"ownSystem":"gxqpt","descrption":"","type":null,"loginable":"true","workStatus":"1","sortValue":null}
     * errmsg : ok
     */

    private int errcode;
    private String data;
    private String errmsg;
    private T result;

    public ResultObj() {
    }

    public ResultObj(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
