package com.pandora.modular.live.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/11.
 */

public class LiveViewHolder extends BaseViewHolder {

    public LiveViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }
}
