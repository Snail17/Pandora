package com.pandora.modular.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/27.
 */

public class HomeAnchorBean {
    private List<String> aUrl;
    private List<String> aWords;
    private String agent_download_url;
    private String banner;
    private List<AnchorData> data;

    public List<String> getaUrl() {
        return aUrl;
    }

    public void setaUrl(List<String> aUrl) {
        this.aUrl = aUrl;
    }

    public List<String> getaWords() {
        return aWords;
    }

    public void setaWords(List<String> aWords) {
        this.aWords = aWords;
    }

    public String getAgent_download_url() {
        return agent_download_url;
    }

    public void setAgent_download_url(String agent_download_url) {
        this.agent_download_url = agent_download_url;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List<AnchorData> getData() {
        return data;
    }

    public void setData(List<AnchorData> data) {
        this.data = data;
    }

    public class AnchorData {
        private String img;
        private String title;
        private String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
