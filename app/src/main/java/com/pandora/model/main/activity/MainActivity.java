package com.pandora.model.main.activity;

import android.os.Bundle;

import com.pandora.R;
import com.pandora.core.base.BaseActivity;
import com.pandora.model.main.bean.TabEntity;
import com.pandora.model.main.widget.BottomBarLayout;

import java.util.List;

public class MainActivity extends BaseActivity {

    private BottomBarLayout mBarLayout;
    private List<TabEntity> mTabs;
    private int[] smallIcon = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    private int[] smallSelectIcon = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    private String[] textList = {"首页", "福利", "购卡", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }


    private void initView() {
        mBarLayout = (BottomBarLayout) findViewById(R.id.main_bottom_bar_layout);
    }

    public void initData() {
        initBottomBar();
    }

    private void initBottomBar() {
        for (int i = 0; i < smallIcon.length; i++) {
            TabEntity tabEntity = new TabEntity();
            tabEntity.setText(textList[i]);
            tabEntity.setNormalIconId(smallIcon[i]);
            tabEntity.setSelectIconId(smallSelectIcon[i]);
            mTabs.add(tabEntity);
        }
        mBarLayout.setTabs(mTabs);
        mBarLayout.setOnItemClickListener(new BottomBarLayout.OnItemClickListener() {
            @Override
            public void onTabClick(int position) {
                mBarLayout.showTab(position);
                switch (position) {
                    case 0:
                        // 切换Fragment 以及 tab
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }
        });
    }


}
