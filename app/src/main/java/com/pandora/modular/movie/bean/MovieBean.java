package com.pandora.modular.movie.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class MovieBean {


    private List<String> aUrl;
    private List<String> aWords; // 广告文
    private List<MovieData> data;
    private String picAreaUrl;
    private String novelUrl;

    public class MovieData {
        private String anchor; // 平台主播个数;
        private String bh;  // 平台编号
        private String image;// 平台图片路径
        private String name; // 平台名称

        public String getAnchor() {
            return anchor;
        }

        public void setAnchor(String anchor) {
            this.anchor = anchor;
        }

        public String getBh() {
            return bh;
        }

        public void setBh(String bh) {
            this.bh = bh;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
