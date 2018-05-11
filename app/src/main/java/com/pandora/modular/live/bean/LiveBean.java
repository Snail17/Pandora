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
    private String utime;
    private List<LiveData> data;

    public class LiveData {
        private String name;
        private String image;
        private String rtmp;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRtmp() {
            return rtmp;
        }

        public void setRtmp(String rtmp) {
            this.rtmp = rtmp;
        }
    }


    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public List<LiveData> getData() {
        return data;
    }

    public void setData(List<LiveData> data) {
        this.data = data;
    }
}
