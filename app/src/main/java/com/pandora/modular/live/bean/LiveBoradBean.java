package com.pandora.modular.live.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/17.
 */

public class LiveBoradBean {

    private String result;
    private String msg;
    private List<BoardData> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BoardData> getData() {
        return data;
    }

    public void setData(List<BoardData> data) {
        this.data = data;
    }

    public class BoardData {
        private String title;
        private String img;
        private String url;

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
    }
}
