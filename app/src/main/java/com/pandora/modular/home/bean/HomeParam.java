package com.pandora.modular.home.bean;

/**
 * Created by Administrator on 2018/5/11.
 */

public class HomeParam {
    private String head;
    private String userId;
    private String agentId;
    private String version;
    private String key;
    private String limit;

    public HomeParam() {
    }

    public HomeParam(String head, String userId, String agentId, String version) {
        this.head = head;
        this.userId = userId;
        this.agentId = agentId;
        this.version = version;
    }

    public HomeParam(String head, String userId, String agentId, String version, String limit) {
        this.head = head;
        this.userId = userId;
        this.agentId = agentId;
        this.version = version;
        this.limit = limit;
    }

    public HomeParam(String head, String userId, String agentId, String version, String key, String limit) {
        this.head = head;
        this.userId = userId;
        this.agentId = agentId;
        this.version = version;
        this.key = key;
        this.limit = limit;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
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
