package com.pandora.modular.movie.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandora.core.utils.GlideLoader.ImageLoaderUtils;
import com.pandora.modular.PandoraApplication;
import com.pandora.modular.movie.bean.MovieBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class MovieRecyclerAdapter extends BaseQuickAdapter<MovieBean.MovieData, MovieViewHolder> {


    public MovieRecyclerAdapter(int layoutResId, @Nullable List<MovieBean.MovieData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(MovieViewHolder helper, MovieBean.MovieData item) {
        helper.cardItemName.setText(item.getMovie_platform_name());
        helper.cardItemCount.setText("普通会员");
        ImageLoaderUtils.displayRound(PandoraApplication.getInstance().getApplicationContext(),
                helper.cardItemImage, item.getMovie_platform_img());
    }

}
