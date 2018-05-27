package com.pandora.modular.movie.bean;

/**
 * Created by Administrator on 2018/5/27.
 */

public class MovieParam {
    private String head;
    private String movie_type;
    private String key;
    private String limit;

    public MovieParam() {
    }

    // 电影界面的请求参数
    public MovieParam(String head, String key) {
        this.head = head;
        this.key = key;
    }

    // 电影列表的请求参数


    public MovieParam(String head, String movie_type, String key, String limit) {
        this.head = head;
        this.movie_type = movie_type;
        this.key = key;
        this.limit = limit;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getMovie_type() {
        return movie_type;
    }

    public void setMovie_type(String movie_type) {
        this.movie_type = movie_type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
