package com.sinothk.rxretrofit.bean;

/**
 * @deprecated 使用PageInfo替换接收数据，后台要使用组装数据方式：
 *
 *              <dependency>
 *                  <groupId>com.github.pagehelper</groupId>
 *                  <artifactId>pagehelper-spring-boot-starter</artifactId>
 *                  <version>1.2.10</version>
 *              </dependency>
 *
 *             PageHelper.startPage(page.getPageIndex(), page.getPageSize());
 *             ArrayList<LoginLogEntity> list = userMapper.findActiveUserList();
 *             PageInfo<LoginLogEntity> pageInfo = new PageInfo<>(list);
 *
 */
@Deprecated
public class PageData<T>{
    private int pageSize;
    private int pageIndex;
    private boolean haveNext;
    private int total;
    private T data;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public boolean isHaveNext() {
        return haveNext;
    }

    public void setHaveNext(boolean haveNext) {
        this.haveNext = haveNext;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
