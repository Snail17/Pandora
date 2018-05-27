package com.pandora.modular.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.pandora.R;
import com.pandora.core.base.BaseActivity;
import com.pandora.core.base.BaseFragment;
import com.pandora.core.utils.FragmentUtil;
import com.pandora.core.utils.LogUtils;
import com.pandora.modular.home.adapter.HomeAnchorRecyclerAdapter;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeParam;
import com.pandora.modular.home.prenster.DaggerHomeAnchorComponent;
import com.pandora.modular.home.prenster.HomeContract;
import com.pandora.modular.home.prenster.HomeModule;
import com.pandora.modular.home.prenster.HomePresenter;
import com.pandora.modular.live.activity.LiveActivity;
import com.pandora.modular.live.activity.LiveBroadcastActivity;
import com.pandora.modular.live.adapter.LiveRecyclerAdapter;
import com.pandora.modular.live.bean.LiveVO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeAnchorFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.recycler_home_anchor_view)
    RecyclerView mRecyclerView;

    private HomeAnchorRecyclerAdapter mAdapter;

    private HomeBean mHomeBean;
    private List<HomeBean.HomeData> mHomeData;

    @Inject
    HomePresenter mHomeAnchorPresenter;

    public HomeAnchorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_home_anchor, container, false);
        ButterKnife.bind(this, retView);
        initData();
        showWaitLoading();
        getData();
        return retView;
    }

    private void initData() {
        mHomeBean = new HomeBean();
        mHomeData = new ArrayList<>();
        DaggerHomeAnchorComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
        mAdapter = new HomeAnchorRecyclerAdapter(R.layout.item_card_live_layout, mHomeData);
        GridLayoutManager manager = new GridLayoutManager(HomeAnchorFragment.this.getContext(), 2);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HomeAnchorFragment.this.getContext(), LiveActivity.class);
                intent.putExtra("videoPath", mHomeBean.getData().get(position).getBh());
                intent.putExtra("videoAuthorIcon", mHomeBean.getData().get(position).getImage());
                intent.putExtra("videoAuthorName", mHomeBean.getData().get(position).getName());
                HomeAnchorFragment.this.startActivity(intent);
            }
        });
    }

    private void getData() {
        HomeParam homeParam = new HomeParam("INIT", "fghjkl", "admin", "1.0");
        mHomeAnchorPresenter.getData(homeParam);
    }

    @Override
    public void setData(String homeJson) {
        LogUtils.e("homeAnchor" + homeJson);
        hideLoading();
        if (!TextUtils.isEmpty(homeJson) && !"null".equals(homeJson)) {
            // 加载完成
            Gson gson = new Gson();
            mHomeBean = gson.fromJson(homeJson, HomeBean.class);
            mHomeData.addAll(mHomeBean.getData());
            mAdapter.notifyDataSetChanged();
        } else {
            // 数据加载完毕
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onErrorData() {
        mAdapter.loadMoreFail();
    }
}
