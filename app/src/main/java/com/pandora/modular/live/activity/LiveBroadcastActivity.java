package com.pandora.modular.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.pandora.R;
import com.pandora.core.base.BaseActivity;
import com.pandora.core.utils.GlideLoader.ImageLoaderUtils;
import com.pandora.core.utils.LogUtils;
import com.pandora.core.utils.widget.CustomTitlebar;
import com.pandora.modular.live.adapter.LiveRecyclerAdapter;
import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.bean.LiveBoradBean;
import com.pandora.modular.live.bean.LiveVO;
import com.pandora.modular.live.presenter.DaggerLiveComponent;
import com.pandora.modular.live.presenter.LiveContract;
import com.pandora.modular.live.presenter.LiveModule;
import com.pandora.modular.live.presenter.LivePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveBroadcastActivity extends BaseActivity implements LiveContract.View, CustomTitlebar.TitleBarOnClickListener {

    @BindView(R.id.recycler_live_view)
    RecyclerView mLiveView;

    @BindView(R.id.title_bar_live)
    CustomTitlebar mCustomTitlebar;
    @BindView(R.id.tv_live_text1)
    TextView tvTitle1;
    @BindView(R.id.iv_live_icon)
    ImageView ivLiveIcon;
    @Inject
    LivePresenter mLivePresenter;
    private LiveBean mLiveBean;
    private LiveBoradBean mLiveBoardBean;
    private List<LiveBean.LiveData> mLiveData;
    private LiveRecyclerAdapter mAdapter;

    private String mFromBH;
    private String mFromImage;
    private String mFromName;

    private boolean isLiveUrl = false;
    private int clickPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_broadcast);
        ButterKnife.bind(this);
        mCustomTitlebar.setAction(this);
        initIntent();
        initData();
        initClick();
    }

    private void initIntent() {
        mFromBH = getIntent().getStringExtra("homeBH");
        mFromImage = getIntent().getStringExtra("homeImage");
        mFromName = getIntent().getStringExtra("homeName");
        ImageLoaderUtils.display(this, ivLiveIcon, mFromImage);
        mCustomTitlebar.setTilte(mFromName);
        tvTitle1.setText(mFromName);
    }

    private void initData() {
        mLiveBean = new LiveBean();
        mLiveData = new ArrayList<>();
        DaggerLiveComponent.builder().liveModule(new LiveModule(this)).build().inject(this);
        mAdapter = new LiveRecyclerAdapter(R.layout.item_card_live_layout, mLiveData);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        mLiveView.setHasFixedSize(true);
        mLiveView.setNestedScrollingEnabled(false);
        mLiveView.setLayoutManager(manager);
        mLiveView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LiveVO liveVO = new LiveVO("PLATFORM", "Android", mFromBH);
                isLiveUrl = true;
                clickPosition = position;
                mLivePresenter.getData(liveVO);
            }
        });
        getData();
    }

    private void getData() {
        LiveVO liveVO = new LiveVO("PLATFORM", "Android", mFromBH);
        mLivePresenter.getData(liveVO);
    }

    private void initClick() {
        mCustomTitlebar.setAction(new CustomTitlebar.TitleBarOnClickListener() {
            @Override
            public void performAction(View view) {
                finish();
            }
        });
        mAdapter.bindToRecyclerView(mLiveView);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData();
            }
        }, mLiveView);

    }

    @Override
    public void setData(String liveJson) {
        LogUtils.e("live" + liveJson);
        if (!TextUtils.isEmpty(liveJson)) {
            Gson gson = new Gson();
            if (!isLiveUrl) {
                mLiveBean = gson.fromJson(liveJson, LiveBean.class);//对于javabean直接给出class实例;
                if (mLiveBean == null | mLiveBean.getData().size() <= 0) {
                    mAdapter.loadMoreEnd();
                    return;
                }
                mLiveData.addAll(mLiveBean.getData());
                mAdapter.notifyDataSetChanged();
                mAdapter.loadMoreComplete();
            } else {
                mLiveBoardBean = gson.fromJson(liveJson, LiveBoradBean.class);//对于javabean直接给出class实例;
                Intent intent = new Intent(LiveBroadcastActivity.this, LiveActivity.class);
                intent.putExtra("videoPath", mLiveBoardBean.getData().get(clickPosition).getUrl());
                intent.putExtra("videoAuthorIcon", mLiveBoardBean.getData().get(clickPosition).getImg());
                intent.putExtra("videoAuthorName", mLiveBoardBean.getData().get(clickPosition).getTitle());
                LiveBroadcastActivity.this.startActivity(intent);
            }
        } else {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onError() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void performAction(View view) {
        finish();
    }
}
