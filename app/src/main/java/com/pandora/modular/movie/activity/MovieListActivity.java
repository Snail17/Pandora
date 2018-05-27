package com.pandora.modular.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.pandora.R;
import com.pandora.core.base.BaseActivity;
import com.pandora.core.utils.LogUtils;
import com.pandora.core.utils.widget.CustomTitlebar;
import com.pandora.modular.movie.adapter.MovieListRecyclerAdapter;
import com.pandora.modular.movie.bean.MovieListBean;
import com.pandora.modular.movie.bean.MovieParam;
import com.pandora.modular.movie.presenter.DaggerMovieListComponent;
import com.pandora.modular.movie.presenter.MovieListContract;
import com.pandora.modular.movie.presenter.MovieListModule;
import com.pandora.modular.movie.presenter.MovieListPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends BaseActivity implements MovieListContract.View {

    @BindView(R.id.title_bar_movie_list)
    CustomTitlebar mTitlebar;

    @BindView(R.id.recycler_movie_list)
    RecyclerView mRecyclerView;
    private MovieListBean mListBean;
    private List<MovieListBean.MovieListData> mListData;
    private MovieListRecyclerAdapter mAdapter;

    private String  movie_type = "CHN";

    @Inject
    MovieListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        initIntent();
        initData();
        initClick();
        showWaitLoading();
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            movie_type = intent.getStringExtra("movie_type");
        }
    }

    private void initData() {
        mListBean = new MovieListBean();
        mListData = new ArrayList<>();
        DaggerMovieListComponent.builder().movieListModule(new MovieListModule(this)).build().inject(this);
        mAdapter = new MovieListRecyclerAdapter(R.layout.item_movie_list_layout, mListData);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        getData();
    }

    private void initClick() {
        mTitlebar.setAction(new CustomTitlebar.TitleBarOnClickListener() {
            @Override
            public void performAction(View view) {

            }
        });
    }

    private void getData() {
        MovieParam param = new MovieParam("MOVIE", movie_type, "keyStr");
        mPresenter.getData(param);
    }

    @Override
    public void setData(String movieJson) {
        LogUtils.e("movieJson" + movieJson);
        hideLoading();
        if (!TextUtils.isEmpty(movieJson) && !"null".equals(movieJson)) {
            Gson gson = new Gson();
            mListBean = gson.fromJson(movieJson, MovieListBean.class);
            mListData.addAll(mListBean.getData());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onErrorData() {

    }
}
