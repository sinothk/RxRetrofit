package com.sinothk.rxRetrofitDemo.retrofit;

import java.util.Date;

/**
 * com.hengyunsoft.platform.mt.api.application.dto
 * 版权：中科恒运软件科技股份有限公司贵阳分公司
 * 描述：应用信息
 * 修改人：gbl
 * 修改时间：2018/5/27
 * 修改内容：
 */
public class ApplicationDTO {
    //"应用id")
    private Long id;

    //"应用名称")
    private String appName;

    //"应用类型(1:安卓,2:h5)")
    private Integer appType;

    // "应用图标")
    private String iconPath;

    // 审核状态(1暂时,2审核中,3通过,4驳回)")
    private Integer state;

    //"应用路径")
    private String appPath;

    //"应用说明")
    private String appDesc;

    //"是否显示(1:不显示 ,2:显示)")
    private Integer isShow;

    // "排序")
    private Integer appOrder;

    //"创建时间(登记时间)")
    private Date createTime;

    public ApplicationDTO() {
    }

    public ApplicationDTO(Integer appOrder) {
        this.appOrder = appOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getAppOrder() {
        return appOrder;
    }

    public void setAppOrder(Integer appOrder) {
        this.appOrder = appOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
