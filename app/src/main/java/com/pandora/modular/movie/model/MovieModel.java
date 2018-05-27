package com.pandora.modular.movie.model;

import com.google.gson.Gson;
import com.pandora.core.utils.webservices.QueryAddressTask;
import com.pandora.modular.movie.bean.MovieParam;
import com.pandora.modular.movie.presenter.OnMovieListListener;

/**
 * Created by Administrator on 2018/5/27.
 */

public class MovieModel {
    public void getData(MovieParam movieParam, final OnMovieListListener listener) {

        QueryAddressTask queryAddressTask = new QueryAddressTask();
        queryAddressTask.setListener(listener);
        String paramValue = new Gson().toJson(movieParam);
        //启动后台任务
        queryAddressTask.execute(paramValue);
    }
}
