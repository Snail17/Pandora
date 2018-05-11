package com.pandora.modular.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandora.R;
import com.pandora.core.base.BaseFragment;
import com.pandora.modular.home.adapter.HomeRecyclerAdapter;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.model.HomeModel;
import com.pandora.modular.home.prenster.DaggerHomeComponent;
import com.pandora.modular.home.prenster.HomeContract;
import com.pandora.modular.home.prenster.HomeModule;
import com.pandora.modular.home.prenster.HomePresenter;
import com.pandora.modular.live.activity.LiveBroadcastActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {


    @BindView(R.id.tv_home_top_introduce)
    TextView introduceText;

    @BindView(R.id.pager_home_top)
    ViewPager mViewPager;

    @BindView(R.id.recycler_home_view)
    RecyclerView mRecyclerView;

    @Inject
    HomePresenter mHomePresenter;
    private HomeRecyclerAdapter mAdapter;

    private HomeBean mHomeBean;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initData();
        initClick();
        return view;
    }

    private void initData() {
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
        mHomePresenter.setModel(new HomeModel());
        List<HomeBean.HomeData> data = mHomePresenter.getData().getData();
        mAdapter = new HomeRecyclerAdapter(R.layout.item_home_card_layout, data);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HomeFragment.this.getContext(), LiveBroadcastActivity.class);
                HomeFragment.this.getContext().startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }


    private void initClick() {
        introduceText.setSelected(true);
    }

    @Override
    public void setData(HomeBean data) {
        mHomeBean = data;
    }


}
