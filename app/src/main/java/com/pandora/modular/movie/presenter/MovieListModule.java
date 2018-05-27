package com.pandora.modular.movie.presenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/27.
 */
@Module
public class MovieListModule {
    private MovieListContract.View view;

    public MovieListModule(MovieListContract.View view) {
        this.view = view;
    }

    @Provides
    MovieListContract.View provideMovieView() {
        return view;
    }
}
