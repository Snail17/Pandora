package com.pandora.core.utils;

import android.os.AsyncTask;

import com.pandora.modular.OnFinishListener;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Administrator on 2018/5/14.
 */

public class WebServiceUtils {

    public static String getRemoteInfo(String paramValue) throws Exception {
        //String WSDL_URI = "[图片]http://192.168.71.101:8888/pandoraService/PrandoraPort?wsdl";//wsdl 的uri
        String WSDL_URI = "http://mj55.top/pandoraService/PrandoraPort?wsdl";
        String namespace = "http://services.pandora.com/";//namespace
        String methodName = "adapter";//要调用的方法名称
        String paramName = "arg0";

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数
        request.addProperty(paramName, paramValue);

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
//        envelope.dotNet = true;//由于是.net开发的webservice，所以这里要设置为true

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);

        String sapAction = namespace + methodName;
        httpTransportSE.call(sapAction, envelope);//调用


        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        String result = object.getProperty(0).toString();
        return result;

    }
}
