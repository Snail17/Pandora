package com.pandora.modular.live.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pandora.R;
import com.pandora.core.base.BaseActivity;
import com.pandora.modular.live.adapter.LiveRecyclerAdapter;
import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.model.LiveModel;
import com.pandora.modular.live.presenter.DaggerLiveComponent;
import com.pandora.modular.live.presenter.LiveContract;
import com.pandora.modular.live.presenter.LiveModule;
import com.pandora.modular.live.presenter.LivePresenter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_broadcast);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mLiveBean = new LiveBean();
        mLiveData = new ArrayList<>();
        DaggerLiveComponent.builder().liveModule(new LiveModule(this)).build().inject(this);
        mLivePresenter.setModel(new LiveModel());
        mAdapter = new LiveRecyclerAdapter(R.layout.item_card_live_layout, mLiveData);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        mLiveView.setHasFixedSize(true);
        mLiveView.setNestedScrollingEnabled(false);
        mLiveView.setLayoutManager(manager);
        mLiveView.setAdapter(mAdapter);
        mLivePresenter.getData();
    }

    @Override
    public void setData(LiveBean data) {
        mLiveBean = data;
        mLiveData.addAll(data.getData());
        mAdapter.notifyDataSetChanged();
    }
}
