package com.pandora.modular.home.model;

import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pandora.core.exception.ExceptionHandle;
import com.pandora.core.http.BaseObserver;
import com.pandora.core.utils.LogUtils;
import com.pandora.modular.PandoraApplication;
import com.pandora.modular.home.api.HomeAPIPModel;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeVO;
import com.pandora.modular.home.prenster.OnHomeFinishListener;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import io.reactivex.Observable;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2018/5/11.
 */

public class HomeModel {

    private String result = "";
    private OnHomeFinishListener mListener;

    public void getData(HomeVO homeVO, final OnHomeFinishListener listener) {

        mListener = listener;
        QueryAddressTask queryAddressTask = new QueryAddressTask();
        //启动后台任务
        queryAddressTask.execute(homeVO);
//
//        Observable<HomeBean> observable = HomeAPIPModel.getInstance().getModelHomeData(new HomeVO());
//        observable.subscribe(new BaseObserver<HomeBean>() {
//            @Override
//            public void onError(ExceptionHandle.ResponeThrowable e) {
//                listener.onError();
//            }
//
//            @Override
//            public void onNext(HomeBean homeBean) {
//                listener.onSuccess(homeBean);
//            }
//        });
    }

    class QueryAddressTask extends AsyncTask<HomeVO, Integer, String> {
        @Override
        protected String doInBackground(HomeVO... params) {
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
            LogUtils.e("home" + result);
            HomeBean homeBean = gson.fromJson(result, HomeBean.class);//对于javabean直接给出class实例
            mListener.onSuccess(homeBean);
//            LogUtils.e(result);
        }
    }

    public String getRemoteInfo(HomeVO homeVO) throws Exception {
        //String WSDL_URI = "[图片]http://192.168.71.101:8888/pandoraService/PrandoraPort?wsdl";//wsdl 的uri
        String WSDL_URI = "http://mj55.top/pandoraService/PrandoraPort?wsdl";
        String namespace = "http://services.pandora.com/";//namespace
        String methodName = "adapter";//要调用的方法名称
        String paramName = "arg0";
        String paramValue = new Gson().toJson(homeVO);
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
