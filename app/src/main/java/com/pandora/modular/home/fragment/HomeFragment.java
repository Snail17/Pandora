package com.pandora.modular.home.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pandora.R;
import com.pandora.core.base.BaseFragment;
import com.pandora.core.utils.FragmentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.tv_hot)
    TextView tvHot;

    @BindView(R.id.tv_anchor)
    TextView tvAnchor;

    private HomeAnchorFragment mHomeAnchorFragment;
    private HomeHotFragment mHomeHotFragment;


    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, ret);
        switchFragment(1);
        return ret;
    }

    @OnClick({R.id.tv_hot, R.id.tv_anchor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_hot:
                switchFragment(1);
                break;
            case R.id.tv_anchor:
                switchFragment(2);
                break;
            default:
                break;
        }
    }

    private void switchFragment(int position) {

        // 切换Fragment 以及 tab
        if (mHomeHotFragment == null) {
            mHomeHotFragment = new HomeHotFragment();
        }
        if (mHomeAnchorFragment == null) {
            mHomeAnchorFragment = new HomeAnchorFragment();
        }
        switch (position) {
            case 1:

                tvHot.setTextColor(getResources().getColor(R.color.blue_00));
                tvAnchor.setTextColor(getResources().getColor(R.color.black));
                FragmentUtil.switchPage(getChildFragmentManager(), R.id.home_container_layout, mHomeAnchorFragment, mHomeHotFragment);
                break;
            case 2:

                tvHot.setTextColor(getResources().getColor(R.color.black));
                tvAnchor.setTextColor(getResources().getColor(R.color.blue_00));
                FragmentUtil.switchPage(getChildFragmentManager(), R.id.home_container_layout, mHomeHotFragment, mHomeAnchorFragment);
                break;
            default:
                break;
        }
    }

}
