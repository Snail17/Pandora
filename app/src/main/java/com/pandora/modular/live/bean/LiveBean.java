package com.pandora.modular.live.bean;


import java.util.List;

/**
 * //主播列表请求JSON格式
 * 'head':'PLATFORM' 固定写法请求头
 * 'platformNo':'Xiaoxiannv' 直播平台编号
 * 例子：
 * {'head':'PLATFORM','platformNo':'Xiaoxiannv'}
 * data 主播列表数组
 * name 主播名称
 * image 主播图片
 * rtmp 主播视频路径
 */

// 直播列表
public class LiveBean {
    private String count;
    private String msg;
    private String total;
    private List<LiveData> data;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<LiveData> getData() {
        return data;
    }

    public void setData(List<LiveData> data) {
        this.data = data;
    }

    public class LiveData {
        private String anchor;
        private String bh;
        private String image;
        private String name;

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
