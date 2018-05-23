package com.pandora.modular.movie.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandora.R;
import com.pandora.core.base.BaseFragment;
import com.pandora.modular.home.widght.RecyclerBanner;
import com.pandora.modular.main.activity.WebViewActivity;
import com.pandora.modular.movie.activity.MovieListActivity;
import com.pandora.modular.movie.adapter.MovieRecyclerAdapter;
import com.pandora.modular.movie.bean.MovieBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends BaseFragment {


    @BindView(R.id.banner_movie)
    RecyclerBanner mBanner;
    @BindView(R.id.tv_movie_top_introduce)
    TextView mTextIntroduce;

    @BindView(R.id.tv_novel)
    TextView mTvNovel;
    @BindView(R.id.tv_pic_area)
    TextView mTvPicArea;

    @BindView(R.id.recycler_movie_view)
    RecyclerView mRecyclerView;


    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();

    private List<MovieBean.MovieData> mMovieData = new ArrayList<>();

    private MovieRecyclerAdapter mAdapter;

    public MovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, ret);
        mTextIntroduce.setSelected(true);
        initData();
        return ret;
    }

    private void initData() {
        urls.add(new Entity("http://pic.58pic.com/58pic/12/46/13/03B58PICXxE.jpg"));
        urls.add(new Entity("http://www.jitu5.com/uploads/allimg/121120/260529-121120232T546.jpg"));
        mBanner.setDatas(urls);
        addMovieData("潘多拉");
        addMovieData("韩国剧情");
        addMovieData("国产");
        addMovieData("动漫");
        addMovieData("主流影视区");
        addMovieData("VIP可look");
        mAdapter = new MovieRecyclerAdapter(R.layout.item_home_card_layout, mMovieData);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 3);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                itemClick(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void itemClick(int position) {

        switch (position) {
            case 0:
                goToList("潘多拉");
                break;
            case 1:
                goToList("韩国剧情");
                break;
            case 2:
                goToList("国产");
                break;
            case 3:
                goToList("动漫");
                break;
            case 4:
                goToWenView("https://www.baidu.com/");
                break;
            case 5:
                Toast.makeText(MovieFragment.this.getContext(), " 该区域仅供钻石会员浏览!!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }

    }

    private void goToList(String listName) {
        Intent intent = new Intent(MovieFragment.this.getContext(), MovieListActivity.class);
        intent.putExtra("listName", listName);
        startActivity(intent);
    }

    private void goToWenView(String url) {
        Intent intent = new Intent(MovieFragment.this.getContext(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.WEB_URL, url);
        startActivity(intent);
    }

    private void addMovieData(String name) {
        MovieBean movieBean = new MovieBean();
        MovieBean.MovieData movieData = movieBean.new MovieData();
        movieData.setName(name);
        movieData.setImage("");
        movieData.setAnchor("普通会员");
        movieData.setBh("xnlnl");
        mMovieData.add(movieData);
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


    @OnClick({R.id.tv_pic_area, R.id.tv_novel})
    public void onClick(View view) {
        Intent intent = new Intent(MovieFragment.this.getContext(), WebViewActivity.class);
        switch (view.getId()) {
            case R.id.tv_pic_area:
                intent.putExtra(WebViewActivity.WEB_URL, "https://www.baidu.com/");
                break;
            case R.id.tv_novel:
                intent.putExtra(WebViewActivity.WEB_URL, "https://www.baidu.com/");
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
