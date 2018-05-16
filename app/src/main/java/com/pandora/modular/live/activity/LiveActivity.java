package com.pandora.modular.live.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import com.pandora.R;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LiveActivity extends AppCompatActivity implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnBufferingUpdateListener, SurfaceHolder.Callback {


    String path = "http://www.modrails.com/videos/passenger_nginx.mov";
    private SurfaceView mSurfaceView;
    private IjkMediaPlayer mIjkMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_live_test);
        mSurfaceView = (SurfaceView) findViewById(R.id.play_video);

        path = getIntent().getStringExtra("videoPath");
        mIjkMediaPlayer = new IjkMediaPlayer();
        // 当播放器加载网络的视频资源时,会在内部进行网络访问
        mIjkMediaPlayer.setOnPreparedListener(this);
        //
        mIjkMediaPlayer.setOnBufferingUpdateListener(this);

        // SurfaceView 准备视频的播放
        if (mSurfaceView != null) {
            mSurfaceView.getHolder().addCallback(this);
        }
        startVideoPlay();
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        iMediaPlayer.start();
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer mp, int percent) {

    }

    private void startVideoPlay() {
        if (path != null) {
            if (!path.isEmpty()) {
                if (path != null) {
                    try {
                        // TODO:应该接着竖屏的地方继续放映
//                        mIjkMediaPlayer.reset();
                        // 设置视频或者音频的地址,需要网络 或者io流的读取
                        mIjkMediaPlayer.setDataSource(path);
                        // 没有同步,异步子线程方式开始准备视频,准备好就回调相应接口
                        mIjkMediaPlayer.prepareAsync();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 当SurfaceView准备好,并且能够显示的时候回调
     * 使用SurfaceHolder 可以进行绘制
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 初始化 播放器或者是照相机, 和setDataResource()一样的作用.
        mIjkMediaPlayer.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    /**
     * surfaceDestroyed 和 onDestroy
     * 在竖屏时,会先执行,onDestroy,然后再surfaceDestroyed
     * 仅限于在本次调试中,视频的横竖屏切换
     * 横屏时 会先执行surfaceDestroyed
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 释放资源, 播放器停止 释放
        if (mIjkMediaPlayer.isPlaying()) {
            mIjkMediaPlayer.stop();
        }
        mIjkMediaPlayer.release();
        Log.d("TAG", "surfaceDestroyed: ");
//        mIjkMediaPlayer = null;
    }
}
