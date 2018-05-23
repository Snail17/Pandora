package com.pandora.modular.splash.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.pandora.R;
import com.pandora.core.base.BaseActivity;
import com.pandora.modular.main.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    public static final int SECOND = 2;
    private int secondCount;
    // 该方法必须不能为private或者是static的
    @BindView(R.id.tv_splash_time)
    TextView tvSplashTime;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (msg.arg1 != 0) {
                        tvSplashTime.setText(msg.arg1 + "s");
                    } else {
                        goToMain();
                    }
                    break;
            }
        }
    };
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        secondCount = SECOND;
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = 1;
                message.arg1 = secondCount--;
                mHandler.handleMessage(message);
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.post(mRunnable);
    }


    @OnClick(R.id.tv_splash_time)
    public void onclick(View view) {
        goToMain();
    }

    private void goToMain() {
        mHandler.removeCallbacks(mRunnable);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
