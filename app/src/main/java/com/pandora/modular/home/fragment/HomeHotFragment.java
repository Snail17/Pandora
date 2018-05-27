package com.pandora.modular.home.fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.pandora.R;
import com.pandora.core.base.BaseFragment;
import com.pandora.core.utils.LogUtils;
import com.pandora.core.utils.MPermissionUtils;
import com.pandora.modular.home.adapter.HomeRecyclerAdapter;
import com.pandora.modular.home.api.HomeAPIPModel;
import com.pandora.modular.home.bean.HomeBean;
import com.pandora.modular.home.bean.HomeParam;
import com.pandora.modular.home.prenster.DaggerHomeComponent;
import com.pandora.modular.home.prenster.HomeContract;
import com.pandora.modular.home.prenster.HomeModule;
import com.pandora.modular.home.prenster.HomePresenter;
import com.pandora.modular.home.util.ProgressListener;
import com.pandora.modular.home.widght.RecyclerBanner;
import com.pandora.modular.live.activity.LiveBroadcastActivity;
import com.pandora.modular.main.activity.WebViewActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeHotFragment extends BaseFragment implements HomeContract.View {

    public static String downloadUpdateApkFilePath = "";

    @BindView(R.id.tv_home_top_introduce)
    TextView introduceText;
    @BindView(R.id.tv_home_ad_notice)
    TextView adNoticeTV;


    @BindView(R.id.recycler_home_hot_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.banner_home)
    RecyclerBanner mBanner;


    @Inject
    HomePresenter mHomePresenter;
    private HomeRecyclerAdapter mAdapter;


    private HomeBean mHomeBean;
    private List<HomeBean.HomeData> mHomeData;
    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();
    private List<String> mAWords = new ArrayList<>();

    private int limitIndex = 1;
    public HomeHotFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_hot, container, false);
        ButterKnife.bind(this, view);
        initData();
        initClick();
        showWaitLoading();
        return view;
    }

    private void initData() {
        mHomeBean = new HomeBean();
        mHomeData = new ArrayList<>();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
        mAdapter = new HomeRecyclerAdapter(R.layout.item_home_card_layout, mHomeData);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 3);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HomeHotFragment.this.getContext(), LiveBroadcastActivity.class);
                intent.putExtra("homeBH", mHomeData.get(position).getBh());
                intent.putExtra("homeImage", mHomeData.get(position).getImage());
                intent.putExtra("homeName", mHomeData.get(position).getName());

                HomeHotFragment.this.getContext().startActivity(intent);
            }
        });
        mAdapter.setNewData(mHomeData);
        for (int i = 0; i < 4; i++) {
            urls.add(new Entity(""));
        }
        getData(limitIndex);
        initBanner();
    }


    private void initBanner() {
        mBanner.setDatas(urls);

        mBanner.setOnPagerClickListener(new RecyclerBanner.OnPagerClickListener() {
            @Override
            public void onClick(RecyclerBanner.BannerEntity entity) {
                Intent intent = new Intent(HomeHotFragment.this.getContext(), WebViewActivity.class);
                intent.putExtra(WebViewActivity.WEB_URL, entity.getUrl());
                startActivity(intent);
            }
        });
    }

    private void initClick() {
        // 设置跑马灯滚动
        introduceText.setSelected(true);
        mAdapter.setUpFetchEnable(false);
        mAdapter.setEnableLoadMore(false);

        //上拉刷新,如果上拉结束后,下拉刷新需要再次开启上拉监听，需要使用setNewData方法填充数据。
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        limitIndex++;
                        getData(limitIndex);
                    }
                }, 1000);
            }
        }, mRecyclerView);
        // 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
//        mAdapter.setPreLoadNumber(5);
//        mAdapter.setLoadMoreView(new CustomLoadMoreView());
    }


    private void getData(int limit) {
        HomeParam homeParam = new HomeParam("INIT", "fghjkl", "admin", "1.0", limit + "");
        mHomePresenter.getData(homeParam);
    }

    public void updateBanner() {
        urls.clear();
        for (int i = 0; i < mHomeBean.getaUrl().size(); i++) {
            urls.add(new Entity(mHomeBean.getaUrl().get(i)));
        }
        mBanner.setDatas(urls);
        mAWords.addAll(mHomeBean.getaWords());
    }

    public void appUpdate() {
        // 如果没有权限
        if (MPermissionUtils.checkPermissions(HomeHotFragment.this.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            if ("Y".equals(mHomeBean.getIsUpdate())) {
                File storageDir = new File(Environment.getExternalStorageDirectory().toString(), "Android");
                downloadUpdateApkFilePath = storageDir.getPath()
                        + File.separator
                        + HomeHotFragment.this.getContext().getPackageName()
                        + "_" + "Pandora" + ".apk";
                final ProgressDialog dialog = new ProgressDialog(HomeHotFragment.this.getContext());
                dialog.setProgressNumberFormat("%1d KB/%2d KB");
                dialog.setTitle("下载");
                dialog.setMessage("正在下载，请稍后...");
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setCancelable(false);
                dialog.show();

                HomeAPIPModel.getInstance().downloadFileProgress(new ProgressListener() {
                    @Override
                    public void onProgress(long currentBytes, long contentLength, boolean done) {
                        dialog.setMax((int) (contentLength / 1024));
                        dialog.setProgress((int) (currentBytes / 1024));
                        if (done) {
                            dialog.dismiss();
                        }
                    }
                }, mHomeBean.getDownload_url());

            }
        } else {
            Toast.makeText(HomeHotFragment.this.getContext(), "您没有SD权限", Toast.LENGTH_SHORT).show();
        }
    }

    private class Entity implements RecyclerBanner.BannerEntity {

        String url;

        public Entity(String url) {
            this.url = url;
        }

        @Override
        public String getUrl() {
            return url;
        }
    }

    @Override
    public void setData(String homeJson) {
        LogUtils.e("home" + homeJson);
        hideLoading();
        if (!TextUtils.isEmpty(homeJson) && !"null".equals(homeJson)) {
            // 加载完成
            Gson gson = new Gson();
            mHomeBean = gson.fromJson(homeJson, HomeBean.class);//对于javabean直接给出class实例;
//            introduceText.setText(mHomeBean.getOnlineService());
            adNoticeTV.setText(mHomeBean.getaWords().get(0));
            mHomeData.addAll(mHomeBean.getData());
            mAdapter.notifyDataSetChanged();
            updateBanner();
            appUpdate();
        } else {
            // 数据加载完毕
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onErrorData() {
        mAdapter.loadMoreFail();
    }
}
