package com.pandora.modular.home.bean;

/**
 * Created by Administrator on 2018/5/11.
 */

public class HomeVO {
    private String head;
    private String userId;
    private String agentId;
    private String version;

    public HomeVO() {
    }

    public HomeVO(String head, String userId, String agentId, String version) {
        this.head = head;
        this.userId = userId;
        this.agentId = agentId;
        this.version = version;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
