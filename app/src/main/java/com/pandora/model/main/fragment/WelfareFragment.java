package com.pandora.model.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pandora.R;
import com.pandora.core.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelfareFragment extends BaseFragment {


    public WelfareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welfare, container, false);
    }

}
