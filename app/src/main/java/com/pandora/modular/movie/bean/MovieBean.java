package com.pandora.modular.movie.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class MovieBean {
    private List<MovieData> data;

    public List<MovieData> getData() {
        return data;
    }

    public void setData(List<MovieData> data) {
        this.data = data;
    }

    public class MovieData {
        private String moive_platform;
        private String id; // 显示顺序ID
        private String movie_platform_name; //电影平台名称
        private String movie_platform_img; // 电影平台图片
        private String movie_type; // 电影平台类型
        private String orderId; // 电影平台类型

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getMoive_platform() {
            return moive_platform;
        }

        public void setMoive_platform(String moive_platform) {
            this.moive_platform = moive_platform;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMovie_platform_name() {
            return movie_platform_name;
        }

        public void setMovie_platform_name(String movie_platform_name) {
            this.movie_platform_name = movie_platform_name;
        }

        public String getMovie_platform_img() {
            return movie_platform_img;
        }

        public void setMovie_platform_img(String movie_platform_img) {
            this.movie_platform_img = movie_platform_img;
        }

        public String getMovie_type() {
            return movie_type;
        }

        public void setMovie_type(String movie_type) {
            this.movie_type = movie_type;
        }
    }


}
