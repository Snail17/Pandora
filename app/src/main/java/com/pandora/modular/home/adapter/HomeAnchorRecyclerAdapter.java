package com.pandora.modular.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandora.core.utils.GlideLoader.ImageLoaderUtils;
import com.pandora.modular.PandoraApplication;
import com.pandora.modular.home.bean.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/27.
 */

public class HomeAnchorRecyclerAdapter extends BaseQuickAdapter<HomeBean.HomeData, HomeAnchorViewholder> {

    public HomeAnchorRecyclerAdapter(int layoutResId, @Nullable List<HomeBean.HomeData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomeAnchorViewholder helper, HomeBean.HomeData item) {
        helper.cardItemCount.setText(item.getName());
        helper.cardItemContent.setText(item.getAnchor());
        ImageLoaderUtils.display(PandoraApplication.getInstance().getApplicationContext(),
                helper.cardItemImage, item.getImage());
    }
}
