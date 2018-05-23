package com.pandora.modular.main.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.pandora.R;
import com.pandora.core.base.BaseActivity;
import com.pandora.core.base.BaseFragment;
import com.pandora.core.globle.PandoraContants;
import com.pandora.core.utils.CustomDialogUtils;
import com.pandora.core.utils.DeviceIdUtils;
import com.pandora.core.utils.FragmentUtil;
import com.pandora.core.utils.MPermissionUtils;
import com.pandora.core.utils.SPUtils;
import com.pandora.modular.PandoraApplication;
import com.pandora.modular.main.bean.TabEntity;
import com.pandora.modular.home.fragment.HomeFragment;
import com.pandora.modular.mine.fragment.MyFragment;
import com.pandora.modular.purchase.fragment.PurchaseFragment;
import com.pandora.modular.Welfare.fragment.WelfareFragment;
import com.pandora.modular.main.widget.BottomBarLayout;

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
    private String[] textList = {"首页", "电影", "购卡", "我的"};

    private HomeFragment homeFragment;
    private WelfareFragment welfareFragment;
    private PurchaseFragment purchaseFragment;
    private MyFragment myFragment;

    protected String[] needPermissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE};
    protected String[] needPermissions26 = {Manifest.permission.REQUEST_INSTALL_PACKAGES};


    private BaseFragment oldFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        getDeviceId();
        showDialog();
    }

    private void showDialog() {
        CustomDialogUtils.showConfirmDialog(this,
                0,
                "是否加入资源更新群",
                "软件现在永久免费，我资源加群！！！",
                "取消",
                "立即加入",
                new CustomDialogUtils.OnDialogClick() {
                    @Override
                    public void cancelClick() {

                    }

                    @Override
                    public void confirmClick() {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PandoraContants.qqUrl)));
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "请检查是否安装QQ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void initView() {
        mBarLayout = (BottomBarLayout) findViewById(R.id.main_bottom_bar_layout);
    }

    public void initData() {
        initBottomBar();
        homeFragment = new HomeFragment();
        oldFragment = homeFragment;
        switchFragment(HOME_INDEX);
        if (Build.VERSION.SDK_INT >= 26) {
            MPermissionUtils.requestPermissionsResult(this, 1, needPermissions26, permissionListener);
        }
        MPermissionUtils.requestPermissionsResult(this, 1, needPermissions, permissionListener);
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
//                FragmentUtil.replacePlug(getSupportFragmentManager(), R.id.main_container_layout, homeFragment);
                FragmentUtil.switchPage(getSupportFragmentManager(), R.id.main_container_layout, oldFragment, homeFragment);
                oldFragment = homeFragment;
                break;
            case WELFARE_INDEX:
                if (welfareFragment == null) {
                    welfareFragment = new WelfareFragment();
                }
//                FragmentUtil.replacePlug(getSupportFragmentManager(), R.id.main_container_layout, welfareFragment);
                FragmentUtil.switchPage(getSupportFragmentManager(), R.id.main_container_layout, oldFragment, welfareFragment);

                oldFragment = welfareFragment;
                break;
            case PURCHSE_INDEX:
                if (purchaseFragment == null) {
                    purchaseFragment = new PurchaseFragment();
                }
//                FragmentUtil.replacePlug(getSupportFragmentManager(), R.id.main_container_layout, purchaseFragment);
                FragmentUtil.switchPage(getSupportFragmentManager(), R.id.main_container_layout, oldFragment, purchaseFragment);

                oldFragment = purchaseFragment;
                break;
            case MY_INDEX:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                }
//                FragmentUtil.replacePlug(getSupportFragmentManager(), R.id.main_container_layout, myFragment);
                FragmentUtil.switchPage(getSupportFragmentManager(), R.id.main_container_layout, oldFragment, myFragment);
                oldFragment = myFragment;
                break;
            default:
                break;
        }
    }


    public String getDeviceId() {
        String deviceId = "Pandora";
        if (MPermissionUtils.checkPermissions(PandoraApplication.getInstance().getApplicationContext(), Manifest.permission.READ_PHONE_STATE)) {
            deviceId = DeviceIdUtils.getDeviceId();
        }
        return deviceId;
    }

    MPermissionUtils.OnPermissionListener permissionListener = new MPermissionUtils.OnPermissionListener() {
        @Override
        public void onPermissionGranted() {
//            SPUtils.putString("devicedId", getDeviceId());
//            SPUtils.putBoolean(SPConstants.REQUEST_PERMISSIONS_SD, true);
//            SPUtils.putBoolean(SPConstants.APP_FIRST_OPEN, false);
        }

        @Override
        public void onPermissionDenied() {
//            SPUtils.putBoolean(SPConstants.REQUEST_PERMISSIONS_SD, false);
//            SPUtils.putBoolean(SPConstants.APP_FIRST_OPEN, false);
        }
    };

}
