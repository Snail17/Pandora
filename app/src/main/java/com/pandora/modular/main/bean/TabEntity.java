package com.pandora.modular.main.bean;

/**
 * Created by Administrator on 2018/5/8.
 */

public class TabEntity {
    private String text;   //tab的名称
    private int normalIconId; //未点击图标的资源id
    private int selectIconId; //点击后图标资源的id
    private boolean isShowPoint;  //是否显示小圆点
    private int newsCount;   //显示消息的数量

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNormalIconId() {
        return normalIconId;
    }

    public void setNormalIconId(int normalIconId) {
        this.normalIconId = normalIconId;
    }

    public int getSelectIconId() {
        return selectIconId;
    }

    public void setSelectIconId(int selectIconId) {
        this.selectIconId = selectIconId;
    }

    public boolean isShowPoint() {
        return isShowPoint;
    }

    public void setShowPoint(boolean showPoint) {
        isShowPoint = showPoint;
    }

    public int getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(int newsCount) {
        this.newsCount = newsCount;
    }
}
