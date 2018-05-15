package com.pandora.modular.home.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 'head':'INIT'  固定写法请求头
 * 'userId':'aaaaabbbccc' 设备唯一码
 * 'agentId':'admin'  代理人号从常量类中 AGENT_ID 获取
 * version:'1.0' 版本号
 * 例子：
 * {'head':'INIT', 'userId':'aaaaabbbccc', 'agentId':'admin', version:'1.0'}
 * //服务端响应JSON
 * /**
 * aUrl   广告图片路径
 * aWords 广告文
 * agent_download_url app下载路径
 * banner   主播页横幅图片路径
 * data   直播平台列表数组
 * anchor 平台主播个数
 * bh  平台编号
 * image 平台图片路径
 * name 平台名称
 * isNewUser 是否新用户
 * isVip 是否VIP
 * isUpdate 是否更新
 * mainNotice 主页滚动文字
 * onlineService 在线客服展示文字
 * popup 是否主页弹出框
 * priceM    会员价格
 * priceM3
 * priceY
 * price_forever
 * total  直播平台数量
 **/
public class HomeBean {
    private List<String> aUrl;
    private List<String> aWords; // 广告文
    private String agent_download_url;
    private String download_url;
    private String banner;
    private List<HomeData> data;

    private String isNewUser;// 是否新用户
    private String isVip; //是否VIP
    private String isUpdate; // 是否更新
    private String mainNotice; // 主页滚动文字
    private String onlineService; // 在线客服展示文字
    private String popup; // 是否主页弹出框
    private String priceM; //  会员价格
    private String priceM3;
    private String priceY;
    private String price_forever;
    private String total; //   直播平台数量

    public  class HomeData {
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

    public List<HomeData> getData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<HomeData> data) {
        this.data = data;
    }

    public String getIsNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(String isNewUser) {
        this.isNewUser = isNewUser;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getIsUpdate() {
//        return isUpdate;
        return "Y";
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getMainNotice() {
        return mainNotice;
    }

    public void setMainNotice(String mainNotice) {
        this.mainNotice = mainNotice;
    }

    public String getOnlineService() {
        return onlineService;
    }

    public void setOnlineService(String onlineService) {
        this.onlineService = onlineService;
    }

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
    }

    public String getPriceM() {
        return priceM;
    }

    public void setPriceM(String priceM) {
        this.priceM = priceM;
    }

    public String getPriceM3() {
        return priceM3;
    }

    public void setPriceM3(String priceM3) {
        this.priceM3 = priceM3;
    }

    public String getPriceY() {
        return priceY;
    }

    public void setPriceY(String priceY) {
        this.priceY = priceY;
    }

    public String getPrice_forever() {
        return price_forever;
    }

    public void setPrice_forever(String price_forever) {
        this.price_forever = price_forever;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDownload_url() {
//        return download_url;
        return "http://dldir1.qq.com/weixin/android/weixin666android1300.apk";
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }
}
