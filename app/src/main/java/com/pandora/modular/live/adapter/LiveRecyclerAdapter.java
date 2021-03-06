package com.pandora.modular.live.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandora.core.utils.GlideLoader.ImageLoaderUtils;
import com.pandora.modular.PandoraApplication;
import com.pandora.modular.live.bean.LiveBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class LiveRecyclerAdapter extends BaseQuickAdapter<LiveBean.LiveData, LiveViewHolder> {

    public LiveRecyclerAdapter(int layoutResId, @Nullable List<LiveBean.LiveData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(LiveViewHolder helper, LiveBean.LiveData item) {
        helper.cardItemCount.setText(item.getTitle());
        helper.cardItemContent.setText(item.getPep());
        ImageLoaderUtils.display(PandoraApplication.getInstance().getApplicationContext(),
                helper.cardItemImage, item.getImg());
    }
}
