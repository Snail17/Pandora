package com.pandora.modular.movie.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/27.
 */

public class MovieListBean {
    private List<MovieListData> data;

    public List<MovieListData> getData() {
        return data;
    }

    public void setData(List<MovieListData> data) {
        this.data = data;
    }

    public class MovieListData {
        private String movie_list; // 电影列表数组
        private String movie_name; // 电影名称
        private String movie_img; // 电影图片
        private String movie_url; // 电影URL
        private String movie_comm; // 电影描述

        public String getMovie_list() {
            return movie_list;
        }

        public void setMovie_list(String movie_list) {
            this.movie_list = movie_list;
        }

        public String getMovie_name() {
            return movie_name;
        }

        public void setMovie_name(String movie_name) {
            this.movie_name = movie_name;
        }

        public String getMovie_img() {
            return movie_img;
        }

        public void setMovie_img(String movie_img) {
            this.movie_img = movie_img;
        }

        public String getMovie_url() {
            return movie_url;
        }

        public void setMovie_url(String movie_url) {
            this.movie_url = movie_url;
        }

        public String getMovie_comm() {
            return movie_comm;
        }

        public void setMovie_comm(String movie_comm) {
            this.movie_comm = movie_comm;
        }
    }
}
