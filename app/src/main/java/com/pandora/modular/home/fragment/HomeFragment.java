package com.pandora.modular.home.fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
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
import com.pandora.modular.home.bean.HomeVO;
import com.pandora.modular.home.prenster.DaggerHomeComponent;
import com.pandora.modular.home.prenster.HomeContract;
import com.pandora.modular.home.prenster.HomeModule;
import com.pandora.modular.home.prenster.HomePresenter;
import com.pandora.modular.home.util.ProgressListener;
import com.pandora.modular.home.widght.RecyclerBanner;
import com.pandora.modular.live.activity.LiveBroadcastActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    public static String downloadUpdateApkFilePath = "";

    @BindView(R.id.tv_home_top_introduce)
    TextView introduceText;
    @BindView(R.id.tv_home_ad_notice)
    TextView adNoticeTV;


    @BindView(R.id.recycler_home_view)
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

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initData();
        initClick();
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
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HomeFragment.this.getContext(), LiveBroadcastActivity.class);
                intent.putExtra("homeBH", mHomeData.get(position).getBh());
                HomeFragment.this.getContext().startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        HomeVO homeVO = new HomeVO("INIT", "fghjkl", "admin", "1.0");

        mHomePresenter.getData(homeVO);
        initBanner();
    }

    private void initBanner() {
        mBanner.setOnPagerClickListener(new RecyclerBanner.OnPagerClickListener() {
            @Override
            public void onClick(RecyclerBanner.BannerEntity entity) {
                Toast.makeText(HomeFragment.this.getContext(), entity.getUrl(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initClick() {
        introduceText.setSelected(true);
    }


    public void updateBanner() {
//        urls.add(new Entity("http://pic.58pic.com/58pic/12/46/13/03B58PICXxE.jpg"));
//        urls.add(new Entity("http://www.jitu5.com/uploads/allimg/121120/260529-121120232T546.jpg"));
//        urls.add(new Entity("http://pic34.nipic.com/20131025/2531170_132447503000_2.jpg"));
//        urls.add(new Entity("http://img5.imgtn.bdimg.com/it/u=3462610901,3870573928&fm=206&gp=0.jpg"));
        for (int i = 0; i < mHomeBean.getaUrl().size(); i++) {
            urls.add(new Entity(mHomeBean.getaUrl().get(i)));
        }
        mBanner.setDatas(urls);
        mAWords.addAll(mHomeBean.getaWords());
//        mBanner.setWords(mHomeBean.getaWords());
    }

    public void appUpdate() {
        // 如果没有权限
        if (MPermissionUtils.checkPermissions(HomeFragment.this.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            if ("Y".equals(mHomeBean.getIsUpdate())) {
                File storageDir = new File(Environment.getExternalStorageDirectory().toString(), "Android");
                downloadUpdateApkFilePath = storageDir.getPath()
                        + File.separator
                        + HomeFragment.this.getContext().getPackageName()
                        + "_" + "Pandora" + ".apk";
                final ProgressDialog dialog = new ProgressDialog(HomeFragment.this.getContext());
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

            } else {
                Toast.makeText(HomeFragment.this.getContext(), "您没有SD权限", Toast.LENGTH_SHORT).show();
            }
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

        if (!TextUtils.isEmpty(homeJson)) {
            Gson gson = new Gson();
            mHomeBean = gson.fromJson(homeJson, HomeBean.class);//对于javabean直接给出class实例;
            introduceText.setText(mHomeBean.getOnlineService());
            adNoticeTV.setText(mHomeBean.getaWords().get(0));
            mHomeData.addAll(mHomeBean.getData());
            mAdapter.notifyDataSetChanged();
            updateBanner();
            appUpdate();
        }

    }

}
