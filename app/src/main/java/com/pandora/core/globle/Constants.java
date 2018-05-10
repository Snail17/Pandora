package com.pandora.core.globle;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2018/5/10.
 */

public class Constants {
    /**
     * 项目配置相关
     */
    String JDBC_DRIVER = "jdbc.driver";
    String JDBC_DATASOURCE_SIZE = "jdbc.datasource.size";
    String JDBC_URL = "jdbc.url";
    String JDBC_USER = "jdbc.user";
    String JDBC_PASSWORD = "jdbc.password";

    int adCount = 4; //广告图片数量

    /* ----------------代理人ID--------------------- */
    String AGENT_ID = "ADMIN";

    int EXPERIENCE = 30; //试看时间

    /* ----------------WebService配置--------------------- */
    String WS_WSDL = "http://mj55.top/pandoraService/PrandoraPort?wsdl";
    String WS_NAMESPACE = "http://services.pandora.com/"; //命名空间
    String WS_METHOD_NAME = "adapter"; //方法名称
    String WS_PARAM_NAME = "arg0"; //参数名称


    String JSON_HEAD = "head"; //json 头

    /* ----------------初始化请求JSON---------------------   */
    //{"head":"INIT","userId":"2asdfa32f","agentId","admin","version":"1.0"}
    String REQUEST_HEAD_INTI = "INIT"; // 初始化JSON请求头
    String REQUEST_USERID = "userId"; //设备唯一码
    String REQUEST_AGENTID = "agentId"; //代理人编号
    String REQUEST_VERSION = "version"; //版本号


    /* ----------------首页响应JSON---------------------   */
    String RESPONSE_HEAD_MAIN = "MAIN"; // 响应JSON头

    String RESPONSE_IS_UPDATE = "isUpdate";//是否更新 Y/N
    String RESPONSE_DOWNLOAD_URL = "download_url"; //APP下载地址
    String RESPONSE_AGENT_DOWNLOAD_URL = "agent_download_url"; //代理人APP下载地址

    String RESPONSE_IS_NEW_USER = "isNewUser"; //是否新用户
    String RESPONSE_IS_VIP = "isVip"; //是否会员
    String RESPONSE_VIP_END_TIME = "vipEntTime"; //会员结束时间

    String RESPONSE_PRICE_M = "priceM"; //会员月价格
    String RESPONSE_PRICE_M3 = "priceM3"; //会员季价格
    String RESPONSE_PRICE_Y = "priceY"; //会员年价格
    String RESPONSE_PRICE_FOREVER = "price_forever"; //永久会员价格
    String RESPONSE_A_URL = "aUrl"; //广告图片 数组
    String RESPONSE_A_WORDS = "aWords";//广告文 数组
    String RESPONSE_MAIN_NOTICE = "mainNotice"; //主页滚动通知
    String RESPONSE_BANNER = "banner"; //直播列表横幅URL
    String RESPONSE_ONLINE_SERVICE = "onlineService"; //在线客服
    String RESPONSE_TOTAL = "total"; //直播平台数量
    String RESPONSE_DATA = "data";//直播平台JSON数组
    String RESPONSE_DATA_PLATFORM_NAME = "name"; //直播平台
    String RESPONSE_DATA_PLATFORM_NO = "bh"; //直播平台编号
    String RESPONSE_DATA_PLATFORM_IMAGE = "image"; //直播平台图片
    String RESPONSE_DATA_PLATFORM_ANCHOR = "anchor"; //主播数量
    String RESPONSE_COMM = "comm";  // 主页弹窗内容
    String RESPONSE_IS_POPUP = "popup"; // 是否主页弹窗


    /* ----------------直播请求JSON---------------------   */
    String REQUEST_HEAD_PLATFORM = "PLATFORM"; //平台接入直播请求头
    String REQUEST_PLATFORM_NO = "platformNo"; //直播平台编号


    /* ----------------直播响应JSON---------------------   */
    String RESPONSE_HEAD_PLATFORM = "PLATFORM"; // 响应JSON头

    String RESPONSE_LB_TOTAL = "total"; //主播数量
    String RESPONSE_LB_DATA = "data"; //主播列表JSON数组
    String RESPONSE_LB_DATA_NAME = "name"; //主播名称
    String RESPONSE_LB_DATA_IMAGE = "image"; //主播图片
    String RESPONSE_LB_DATA_RTPM = ""; //主播直播URL

    /* ----------------卡密激活请求JSON---------------------   */
    String REQUEST_HEAD_VIP_CARD = "CARD";

    String REQUEST_VIP_CARD_NO = "cardNo";
    String REQUEST_VIP_AGENT_ID = "agentId";
    String REQUEST_VIP_USER_ID = "userId";

    /* ----------------卡密响应请求JSON---------------------   */
    String RESPONSE_HEAD_VIP_CARD = "CARD";

    String RESPONSE_VIP_IS_OK = "isOk"; // 是否激活成功 Y/N
    String RESPONSE_VIP_VIP_END_TIME = "vipEndTime"; //VIP到期时间


}
