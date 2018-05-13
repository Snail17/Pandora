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
    private String msg;
    private String result;
    private List<LiveData> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<LiveData> getData() {
        return data;
    }

    public void setData(List<LiveData> data) {
        this.data = data;
    }

    public class LiveData {
        private String title;
        private String img;
        private String url;
        private String pep;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPep() {
            return pep;
        }

        public void setPep(String pep) {
            this.pep = pep;
        }
    }
}
