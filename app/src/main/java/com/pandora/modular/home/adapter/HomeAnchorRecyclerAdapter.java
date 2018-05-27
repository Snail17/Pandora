package com.pandora.modular.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandora.core.utils.GlideLoader.ImageLoaderUtils;
import com.pandora.modular.PandoraApplication;
import com.pandora.modular.home.bean.HomeAnchorBean;
import com.pandora.modular.home.bean.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/27.
 */

public class HomeAnchorRecyclerAdapter extends BaseQuickAdapter<HomeAnchorBean.AnchorData, HomeAnchorViewholder> {

    public HomeAnchorRecyclerAdapter(int layoutResId, @Nullable List<HomeAnchorBean.AnchorData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomeAnchorViewholder helper, HomeAnchorBean.AnchorData item) {
        helper.cardItemCount.setText(item.getTitle());
        helper.cardItemContent.setText(item.getTitle());
        ImageLoaderUtils.display(PandoraApplication.getInstance().getApplicationContext(),
                helper.cardItemImage, item.getImg());
    }
}
