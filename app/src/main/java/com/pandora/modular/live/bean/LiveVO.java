package com.pandora.modular.live.bean;

/**
 * Created by Administrator on 2018/5/12.
 */

public class LiveVO {
    private String head;
    private String platformNo;

    public LiveVO(String head, String platformNo) {
        this.head = head;
        this.platformNo = platformNo;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }


    public String getPlatformNo() {
        return platformNo;
    }

    public void setPlatformNo(String platformNo) {
        this.platformNo = platformNo;
    }
}
