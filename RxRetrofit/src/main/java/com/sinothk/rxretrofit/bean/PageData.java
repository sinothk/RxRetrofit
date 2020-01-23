package com.sinothk.rxretrofit.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <pre>
 *  创建:  梁玉涛 2019/4/30 on 14:03
 *  项目:  RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public class PageData<T> implements Serializable {

    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;

    //总页数
    private int pageTotal;
    //总记录数
    private int total;

    //结果集
    private ArrayList<T> list;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }
}
