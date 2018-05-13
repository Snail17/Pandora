package com.pandora.modular.live.model;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.pandora.core.utils.LogUtils;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;
import com.pandora.modular.home.model.HomeModel;
import com.pandora.modular.home.prenster.OnHomeFinishListener;
import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.bean.LiveVO;
import com.pandora.modular.live.presenter.OnLiveFinishListener;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


/**
 * Created by Administrator on 2018/5/11.
 */

public class LiveModel {

    private String result = "";
    private OnLiveFinishListener mListener;

    public void getLiveData(LiveVO liveVO, final OnLiveFinishListener listener) {
        mListener = listener;
        QueryAddressTask queryAddressTask = new QueryAddressTask();
        //启动后台任务
        queryAddressTask.execute(liveVO);
    }

    class QueryAddressTask extends AsyncTask<LiveVO, Integer, String> {
        @Override
        protected String doInBackground(LiveVO... params) {
            // 查询手机号码（段）信息*/
            try {
                result = getRemoteInfo(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //将结果返回给onPostExecute方法
            return result;
        }

        @Override
        //此方法可以在主线程改变UI
        protected void onPostExecute(String result) {
            Gson gson = new Gson();
            LogUtils.e("live" + result);
            LiveBean liveBean = gson.fromJson(result, LiveBean.class);//对于javabean直接给出class实例
            mListener.onSuccess(liveBean);
        }
    }

    public String getRemoteInfo(LiveVO liveVO) throws Exception {
        //String WSDL_URI = "[图片]http://192.168.71.101:8888/pandoraService/PrandoraPort?wsdl";//wsdl 的uri
        String WSDL_URI = "http://mj55.top/pandoraService/PrandoraPort?wsdl";
        String namespace = "http://services.pandora.com/";//namespace
        String methodName = "adapter";//要调用的方法名称
        String paramName = "arg0";
        String paramValue = new Gson().toJson(liveVO);

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
