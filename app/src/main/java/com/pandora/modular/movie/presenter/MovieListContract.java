package com.pandora.modular.movie.presenter;

import com.pandora.modular.movie.bean.MovieParam;

/**
 * Created by Administrator on 2018/5/27.
 */

public interface MovieListContract {
    interface View {
        void setData(String movieJson);

        void onErrorData();
    }

    interface Presenter {
        void getData(MovieParam movieParam);
    }
}
