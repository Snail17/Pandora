package com.pandora.core.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/5/8.
 */

public class BaseActivity extends AppCompatActivity {

    private int tid;
    private Handler handler;
    private Toast toast;

    /**
     * @param savedInstanceState s
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getAppManager().addActivity(this);
        tid = Process.myTid();
        handler = new Handler();

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


    /**
     *
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
        handler.removeCallbacksAndMessages(null);
        toast = null;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

