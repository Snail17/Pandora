package com.pandora.core.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.pandora.R;
import com.pandora.core.utils.widget.CustomLoadingUtil;
import com.pandora.modular.PandoraApplication;

/**
 * Created by Administrator on 2018/5/8.
 */

public class BaseActivity extends AppCompatActivity {

    private int tid;
    private Handler handler;
    private Toast toast;

    private Context mContext;

    /**
     * @param savedInstanceState s
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppManager.getAppManager().addActivity(this);
        tid = Process.myTid();
        handler = new Handler();
        mContext = this;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    protected boolean isValidContext(Activity a) {
        if (Build.VERSION.SDK_INT >= 17) {
            return !(a.isDestroyed() || a.isFinishing());
        } else {
            return !a.isFinishing();
        }
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

    /**
     *
     *
     */
    @Override
    protected void onDestroy() {
        CustomLoadingUtil.closeDialog();
        AppManager.getAppManager().removeActivity(this);
        handler.removeCallbacksAndMessages(null);
        toast = null;
        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

