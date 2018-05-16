package com.pandora.modular.home.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandora.core.utils.GlideLoader.ImageLoaderUtils;
import com.pandora.modular.PandoraApplication;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.fragment.HomeFragment;
import com.pandora.modular.home.util.ChangeCharset;
import com.pandora.modular.live.activity.LiveBroadcastActivity;

import java.io.UnsupportedEncodingException;
import java.util.List;


public class HomeRecyclerAdapter extends BaseQuickAdapter<HomeBean.HomeData, HomeViewHolder> {


    public HomeRecyclerAdapter(int layoutResId, @Nullable List<HomeBean.HomeData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomeViewHolder helper, HomeBean.HomeData item) {
        helper.cardItemName.setText(item.getName());
        helper.cardItemCount.setText(item.getAnchor());
        ImageLoaderUtils.displayRound(PandoraApplication.getInstance().getApplicationContext(),
                helper.cardItemImage, item.getImage());
    }

    public  String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }
}
