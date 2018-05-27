package com.pandora.modular.movie.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.pandora.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/27.
 */

public class MovieListViewHolder extends BaseViewHolder {

    @BindView(R.id.iv_item_movie_list_platform_image)
    ImageView platformImage;
    @BindView(R.id.tv_movie_list_platform_name)
    TextView platformName;

    public MovieListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
