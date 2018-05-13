package com.pandora.modular.live.bean;

/**
 * Created by Administrator on 2018/5/12.
 */

public class LiveVO {
    private String head;
    private String platform;
    private String platformNo;

    public LiveVO(String head, String platform, String platformNo) {
        this.head = head;
        this.platform = platform;
        this.platformNo = platformNo;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatformNo() {
        return platformNo;
    }

    public void setPlatformNo(String platformNo) {
        this.platformNo = platformNo;
    }
}
