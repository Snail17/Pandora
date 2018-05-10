package com.pandora.modular.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pandora.R;
import com.pandora.core.base.BaseFragment;
import com.pandora.modular.live.activity.LiveBroadcastActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.tv_home_top_introduce)
    TextView introduceText;

    @BindView(R.id.pager_home_top)
    ViewPager mViewPager;

    @BindView(R.id.recycler_home_view)
    RecyclerView mRecyclerView;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initClick();
        return view;
    }

    private void initClick() {
        introduceText.setSelected(true);
    }

//    @OnItemClick(R.id.recycler_home_view)
//    void onItemSelected(int position) {
//        Intent intent = new Intent(this.getActivity(), LiveBroadcastActivity.class);
//        startActivity(intent);
//    }

}
