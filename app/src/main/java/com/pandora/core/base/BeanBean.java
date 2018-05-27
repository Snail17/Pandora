package com.pandora.core.base;

/**
 * Created by Administrator on 2018/5/27.
 */

public class BeanBean<T> {
    private String head;

    private String key;

    private T t;


    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
