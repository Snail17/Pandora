package com.pandora.core.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.pandora.core.utils.widget.CustomLoadingUtil;
import com.pandora.modular.PandoraApplication;
import com.pandora.modular.home.fragment.HomeHotFragment;

/**
 * Created by Administrator on 2018/5/8.
 */

public class BaseFragment extends Fragment {

    private BaseActivity activity;

    private Context mContext;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        activity = (BaseActivity) context;
    }

    public void showLoading() {
        CustomLoadingUtil.showWaitDialog(mContext, false, new CustomLoadingUtil.ILoadingClose() {
            @Override
            public void loadingClose() {
//                Toast.makeText(PandoraApplication.getInstance().getApplicationContext(), "close", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showWaitLoading() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                CustomLoadingUtil.showWaitDialog(mContext, false, new CustomLoadingUtil.ILoadingClose() {
                    @Override
                    public void loadingClose() {
                        Toast.makeText(PandoraApplication.getInstance().getApplicationContext(), "close", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void hideLoading() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CustomLoadingUtil.hideDialog();
            }
        }, 1000);
    }

    @Override
    public void onDestroy() {
        CustomLoadingUtil.closeDialog();
        super.onDestroy();
    }
}
