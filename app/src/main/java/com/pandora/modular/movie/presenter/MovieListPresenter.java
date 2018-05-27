package com.pandora.modular.movie.presenter;

import com.pandora.modular.movie.bean.MovieParam;
import com.pandora.modular.movie.model.MovieModel;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/27.
 */

public class MovieListPresenter implements MovieListContract.Presenter, OnMovieListListener {
    private MovieListContract.View view;
    private MovieModel model;

    @Inject
    public MovieListPresenter(MovieListContract.View view) {
        model = new MovieModel();
        this.view = view;
    }


    @Override
    public void getData(MovieParam movieParam) {
        model.getData(movieParam, this);
    }

    @Override
    public void onError() {
        view.onErrorData();
    }

    @Override
    public void onSuccess(String resultJson) {
        view.setData(resultJson);
    }
}
