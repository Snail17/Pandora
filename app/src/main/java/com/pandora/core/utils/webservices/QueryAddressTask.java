package com.pandora.core.utils.webservices;

import android.os.AsyncTask;

import com.pandora.core.utils.LogUtils;
import com.pandora.core.utils.WebServiceUtils;
import com.pandora.core.base.OnFinishListener;

/**
 * Created by Administrator on 2018/5/14.
 */

public class QueryAddressTask extends AsyncTask<String, Integer, String> {

    private String result = "";
    private OnFinishListener mListener;

    public void setListener(OnFinishListener listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        // 查询手机号码（段）信息*/
        try {
            result = WebServiceUtils.getRemoteInfo(params[0]);
        } catch (Exception e) {
            mListener.onError();
            e.printStackTrace();
        }
        //将结果返回给onPostExecute方法
        return result;
    }

    @Override
    //此方法可以在主线程改变UI
    protected void onPostExecute(String result) {
        mListener.onSuccess(result);
    }
}
