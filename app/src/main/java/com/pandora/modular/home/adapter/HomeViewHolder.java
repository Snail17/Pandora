package com.pandora.modular.home.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.pandora.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2018/5/10.
 */

public class HomeViewHolder extends BaseViewHolder {

    @BindView(R.id.iv_home_card_image)
    ImageView cardItemImage;

    @BindView(R.id.tv_home_card_name)
    TextView cardItemName;

    @BindView(R.id.tv_home_card_count)
    TextView cardItemCount;

    public HomeViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

}
