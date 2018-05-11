package com.pandora.modular.home.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.fragment.HomeFragment;
import com.pandora.modular.live.activity.LiveBroadcastActivity;

import java.util.List;


public class HomeRecyclerAdapter extends BaseQuickAdapter<HomeBean.HomeData, HomeViewHolder> {


    public HomeRecyclerAdapter(int layoutResId, @Nullable List<HomeBean.HomeData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomeViewHolder helper, HomeBean.HomeData item) {
        helper.cardItemName.setText(item.getName());
        helper.cardItemCount.setText(item.getAnchor());
    }
}
