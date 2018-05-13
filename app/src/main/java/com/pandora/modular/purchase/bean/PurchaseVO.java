package com.pandora.modular.purchase.bean;

/**
 * Created by Administrator on 2018/5/13.
 */

public class PurchaseVO {
    private String head;
    private String cardNo;
    private String agentId;
    private String userId;

    public PurchaseVO(String head, String cardNo, String agentId, String userId) {
        this.head = head;
        this.cardNo = cardNo;
        this.agentId = agentId;
        this.userId = userId;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
