package com.pandora.modular.live.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.pandora.R;
import com.pandora.core.base.BaseActivity;
import com.pandora.modular.live.bean.LiveBean;
import com.pandora.modular.live.presenter.LiveContract;
import com.pandora.modular.live.presenter.LivePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveBroadcastActivity extends BaseActivity implements LiveContract.View {

    @BindView(R.id.recycler_live_view)
    RecyclerView mLiveView;

    @Inject
    LivePresenter mLivePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_broadcast);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
    }

    @Override
    public void setData(LiveBean data) {

    }
}
