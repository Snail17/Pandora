package com.pandora.model.main.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.pandora.R;
import com.pandora.core.base.BaseActivity;
import com.pandora.core.utils.FragmentUtil;
import com.pandora.model.main.bean.TabEntity;
import com.pandora.model.main.fragment.HomeFragment;
import com.pandora.model.main.fragment.MyFragment;
import com.pandora.model.main.fragment.PurchaseFragment;
import com.pandora.model.main.fragment.WelfareFragment;
import com.pandora.model.main.widget.BottomBarLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    public static final int HOME_INDEX = 0;
    public static final int WELFARE_INDEX = 1;
    public static final int PURCHSE_INDEX = 2;
    public static final int MY_INDEX = 3;

    private BottomBarLayout mBarLayout;
    private List<TabEntity> mTabs;
    private int[] smallIcon = {R.drawable.icon_home_normal, R.drawable.icon_home_task_normal, R.drawable.icon_home_invite_normal, R.drawable.icon_home_person_normal};
    private int[] smallSelectIcon = {R.drawable.icon_home_pressed, R.drawable.icon_home_task_pressed, R.drawable.icon_home_invite_pressed, R.drawable.icon_home_person_pressed};
    private String[] textList = {"首页", "福利", "购卡", "我的"};

    private HomeFragment homeFragment;
    private WelfareFragment welfareFragment;
    private PurchaseFragment purchaseFragment;
    private MyFragment myFragment;

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
        switchFragment(HOME_INDEX);
    }

    private void initBottomBar() {
        mTabs = new ArrayList<>();
        for (int i = 0; i < smallIcon.length; i++) {
            TabEntity tabEntity = new TabEntity();
            tabEntity.setText(textList[i]);
            tabEntity.setNormalIconId(smallIcon[i]);
            tabEntity.setSelectIconId(smallSelectIcon[i]);
            mTabs.add(tabEntity);
        }
        mBarLayout.setTabs(mTabs);
        mBarLayout.setTabList(this, HOME_INDEX);
        mBarLayout.setOnItemClickListener(new BottomBarLayout.OnItemClickListener() {
            @Override
            public void onTabClick(int position) {
                mBarLayout.showTab(position);
                switchFragment(position);
            }
        });
    }

    public void switchFragment(int position) {
        switch (position) {
            case HOME_INDEX:
                // 切换Fragment 以及 tab
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                FragmentUtil.replacePlug(getSupportFragmentManager(), R.id.main_container_layout, homeFragment);
                break;
            case WELFARE_INDEX:
                if (welfareFragment == null) {
                    welfareFragment = new WelfareFragment();
                }
                FragmentUtil.replacePlug(getSupportFragmentManager(), R.id.main_container_layout, welfareFragment);
                break;
            case PURCHSE_INDEX:
                if (purchaseFragment == null) {
                    purchaseFragment = new PurchaseFragment();
                }
                FragmentUtil.replacePlug(getSupportFragmentManager(), R.id.main_container_layout, purchaseFragment);
                break;
            case MY_INDEX:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                }
                FragmentUtil.replacePlug(getSupportFragmentManager(), R.id.main_container_layout, myFragment);
                break;
            default:
                break;
        }
    }


}
