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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.pandora.R;
import com.pandora.core.base.BaseFragment;
import com.pandora.core.utils.FragmentUtil;
import com.pandora.core.utils.LogUtils;
import com.pandora.modular.home.adapter.HomeAnchorRecyclerAdapter;
import com.pandora.modular.home.bean.HomeAnchorBean;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeParam;
import com.pandora.modular.home.prenster.DaggerHomeAnchorComponent;
import com.pandora.modular.home.prenster.HomeContract;
import com.pandora.modular.home.prenster.HomeModule;
import com.pandora.modular.home.prenster.HomePresenter;
import com.pandora.modular.live.activity.LiveActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeAnchorFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.recycler_home_anchor_view)
    RecyclerView mRecyclerView;

    private HomeAnchorRecyclerAdapter mAdapter;

    private HomeAnchorBean mAnchorBean;
    private List<HomeAnchorBean.AnchorData> mAnchorData;

    @Inject
    HomePresenter mHomeAnchorPresenter;

    private int limitIndex = 1;

    public HomeAnchorFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_home_anchor, container, false);
        ButterKnife.bind(this, retView);
        initData();
        initClick();
        showWaitLoading();

        return retView;
    }

    private void initData() {
        mAnchorBean = new HomeAnchorBean();
        mAnchorData = new ArrayList<>();
        DaggerHomeAnchorComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
        mAdapter = new HomeAnchorRecyclerAdapter(R.layout.item_card_live_layout, mAnchorData);
        GridLayoutManager manager = new GridLayoutManager(HomeAnchorFragment.this.getContext(), 2);
        mRecyclerView.setLayoutManager(manager);
        // 加上此句，RecyclerView请求到数据之后就展示
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HomeAnchorFragment.this.getContext(), LiveActivity.class);
                intent.putExtra("videoPath", mAnchorBean.getData().get(position).getUrl());
                intent.putExtra("videoAuthorIcon", mAnchorBean.getData().get(position).getImg());
                intent.putExtra("videoAuthorName", mAnchorBean.getData().get(position).getTitle());
                HomeAnchorFragment.this.startActivity(intent);
            }
        });
        getData(limitIndex);
    }

    public void initClick() {
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                limitIndex++;
                getData(limitIndex);
            }
        }, mRecyclerView);
    }

    private void getData(int limit) {
        HomeParam homeParam = new HomeParam("ANCHORSQUARE", "fghjkl", "admin", "1.0", limit + "");
        mHomeAnchorPresenter.getData(homeParam);
    }

    @Override
    public void setData(String homeJson) {
        LogUtils.e("homeAnchor" + homeJson);
        hideLoading();
        if (!TextUtils.isEmpty(homeJson) && !"null".equals(homeJson)) {
            // 加载完成
            Gson gson = new Gson();
            mAnchorBean = gson.fromJson(homeJson, HomeAnchorBean.class);
            if (mAnchorBean == null | mAnchorBean.getData().size() <= 0) {
                mAdapter.loadMoreEnd();
                return;
            }
            mAnchorData.addAll(mAnchorBean.getData());
            mAdapter.notifyDataSetChanged();
            mAdapter.loadMoreComplete();
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
