package com.pandora.modular.live.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pandora.R;
import com.pandora.core.utils.GlideLoader.ImageLoaderUtils;
import com.pandora.modular.live.view.PlayStateParams;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LiveActivity extends AppCompatActivity implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnBufferingUpdateListener, SurfaceHolder.Callback, IMediaPlayer.OnInfoListener {


    String path = "http://www.modrails.com/videos/passenger_nginx.mov";
    private IjkMediaPlayer mIjkMediaPlayer;

    @BindView(R.id.pb_live)
    ProgressBar mProgressBar;

    @BindView(R.id.iv_close_live_bottom)
    ImageView ivBottomClose;
    @BindView(R.id.iv_close_live_top)
    ImageView ivTopClose;
    @BindView(R.id.iv_icon)
    ImageView icIcon;
    @BindView(R.id.ic_video_error)
    ImageView icVideoErrorIcon;
    @BindView(R.id.pb_layout)
    LinearLayout mPbLayout;

    @BindView(R.id.tv_list_name)
    TextView tvName;

    @BindView(R.id.play_video)
    SurfaceView mSurfaceView;
    private String mVideoAuthorIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_live_test);
        ButterKnife.bind(this);
        initPlayer();
        initIntent();
        initClick();
        startVideoPlay();
    }

    private void initPlayer() {
        mIjkMediaPlayer = new IjkMediaPlayer();
        // 当播放器加载网络的视频资源时,会在内部进行网络访问
        mIjkMediaPlayer.setOnPreparedListener(this);
        //
        mIjkMediaPlayer.setOnBufferingUpdateListener(this);
        mIjkMediaPlayer.setOnInfoListener(this);

        // SurfaceView 准备视频的播放
        if (mSurfaceView != null) {
            mSurfaceView.getHolder().addCallback(this);
        }
        // 设置屏幕常亮
        mIjkMediaPlayer.setScreenOnWhilePlaying(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initClick() {
        mIjkMediaPlayer.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                ImageLoaderUtils.display(LiveActivity.this, icVideoErrorIcon, mVideoAuthorIcon);
                return false;
            }
        });
    }

    private void initIntent() {
        String videoPath = getIntent().getStringExtra("videoPath");
        mVideoAuthorIcon = getIntent().getStringExtra("videoAuthorIcon");
        String videoAuthorName = getIntent().getStringExtra("videoAuthorName");
        if (!TextUtils.isEmpty(videoPath)) {
            path = videoPath;
        }
        if (!TextUtils.isEmpty(mVideoAuthorIcon)) {
            ImageLoaderUtils.displayRound(this, icIcon, mVideoAuthorIcon);
        }
        if (!TextUtils.isEmpty(videoAuthorName)) {
            tvName.setText(videoAuthorName);
        }
    }

    private void startVideoPlay() {
        showStatus();
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


    @OnClick({R.id.iv_close_live_top, R.id.iv_close_live_bottom})
    public void viewClick(View view) {
        destroyPlayer();
        finish();
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        iMediaPlayer.start();
        mPbLayout.setVisibility(View.GONE);
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer mp, int percent) {
        // 在这里可以计算视频缓冲区进度
        hideStatus();
    }

    @Override
    public boolean onInfo(IMediaPlayer mp, int what, int extra) {
        setChange(what);
        return false;
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
        destroyPlayer();

    }

    /**
     * 视频的状态处理，进行通知栏 缓冲progressbar 显示不显示
     *
     * @param newStatus
     */
    private void setChange(int newStatus) {
        if (newStatus == PlayStateParams.MEDIA_INFO_VIDEO_RENDERING_START
                || newStatus == PlayStateParams.STATE_PLAYING
                || newStatus == PlayStateParams.STATE_PREPARED
                || newStatus == PlayStateParams.MEDIA_INFO_BUFFERING_END
                || newStatus == PlayStateParams.STATE_PAUSED) {
            // 缓冲结束
            hideStatus();
        } else if (newStatus == PlayStateParams.STATE_PREPARING
                || newStatus == PlayStateParams.MEDIA_INFO_BUFFERING_START) {
            // 缓冲中
            showStatus();
        } else if (newStatus == PlayStateParams.STATE_COMPLETED) {
            // 播放完毕

        }
    }

    private void hideStatus() {
        mPbLayout.setVisibility(View.GONE);
    }

    private void showStatus() {
        mPbLayout.setVisibility(View.VISIBLE);
    }

    private void destroyPlayer() {
        // 释放资源, 播放器停止 释放
        if (mIjkMediaPlayer.isPlaying()) {
            mIjkMediaPlayer.stop();
        }
        mIjkMediaPlayer.release();
        Log.d("TAG", "surfaceDestroyed: ");
    }


}
