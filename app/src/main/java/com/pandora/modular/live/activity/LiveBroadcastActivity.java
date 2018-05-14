package com.pandora.modular.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.pandora.R;
import com.pandora.core.base.BaseActivity;
import com.pandora.core.utils.LogUtils;
import com.pandora.modular.live.adapter.LiveRecyclerAdapter;
import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.bean.LiveVO;
import com.pandora.modular.live.model.LiveModel;
import com.pandora.modular.live.presenter.DaggerLiveComponent;
import com.pandora.modular.live.presenter.LiveContract;
import com.pandora.modular.live.presenter.LiveModule;
import com.pandora.modular.live.presenter.LivePresenter;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveBroadcastActivity extends BaseActivity implements LiveContract.View {

    @BindView(R.id.recycler_live_view)
    RecyclerView mLiveView;

    @Inject
    LivePresenter mLivePresenter;

    private LiveBean mLiveBean;
    private List<LiveBean.LiveData> mLiveData;
    private LiveRecyclerAdapter mAdapter;
    private String mPlatformNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_broadcast);
        ButterKnife.bind(this);
        initIntent();
        initData();
    }

    private void initIntent() {
        mPlatformNo = getIntent().getStringExtra("platformNo");
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
                Intent intent = new Intent(LiveBroadcastActivity.this, LiveActivity.class);
                intent.putExtra("videoUrl", mLiveData.get(position).getUrl());
                LiveBroadcastActivity.this.startActivity(intent);
            }
        });
        LiveVO liveVO = new LiveVO("INIT", "Android", mPlatformNo);
        mLivePresenter.getData(liveVO);
    }

    @Override
    public void setData(String liveJson) {
        LogUtils.e("live" + liveJson);
        if (!TextUtils.isEmpty(liveJson)) {
            Gson gson = new Gson();
            mLiveBean = gson.fromJson(liveJson, LiveBean.class);//对于javabean直接给出class实例;
            mLiveData.addAll(mLiveBean.getData());
            mAdapter.notifyDataSetChanged();
        }
    }
}
