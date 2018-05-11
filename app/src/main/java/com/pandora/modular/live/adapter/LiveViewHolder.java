package com.pandora.modular.live.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.pandora.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/11.
 */

public class LiveViewHolder extends BaseViewHolder {

    @BindView(R.id.iv_live_card_image)
    ImageView cardItemImage;

    @BindView(R.id.tv_live_card_count)
    TextView cardItemCount;

    @BindView(R.id.tv_live_card_content)
    TextView cardItemContent;

    public LiveViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }
}
