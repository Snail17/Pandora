package com.pandora.modular.movie.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandora.core.utils.GlideLoader.ImageLoaderUtils;
import com.pandora.modular.PandoraApplication;
import com.pandora.modular.movie.bean.MovieListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/27.
 */

public class MovieListRecyclerAdapter extends BaseQuickAdapter<MovieListBean.MovieListData, MovieListViewHolder> {


    public MovieListRecyclerAdapter(int layoutResId, @Nullable List<MovieListBean.MovieListData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(MovieListViewHolder helper, MovieListBean.MovieListData item) {
        helper.platformName.setText(item.getMovie_name());
        ImageLoaderUtils.display(PandoraApplication.getInstance().getApplicationContext(),
                 helper.platformImage, item.getMovie_url());
    }
}