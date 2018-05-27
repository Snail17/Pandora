package com.pandora.modular.movie.presenter;

import com.pandora.modular.movie.activity.MovieListActivity;

import dagger.Component;
import dagger.Module;

/**
 * Created by Administrator on 2018/5/27.
 */
@Component(modules = MovieListModule.class)
public interface MovieListComponent {
    void inject(MovieListActivity activity);
}
