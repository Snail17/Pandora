package com.pandora.modular.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandora.modular.home.bean.HomeBean;

import java.util.List;


public class HomeRecyclerAdapter extends BaseQuickAdapter<HomeBean, HomeViewHolder> {
    public HomeRecyclerAdapter(int layoutResId, @Nullable List<HomeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomeViewHolder helper, HomeBean item) {

    }
}
