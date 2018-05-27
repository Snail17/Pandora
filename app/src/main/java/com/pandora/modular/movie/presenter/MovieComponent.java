package com.pandora.modular.movie.presenter;

import com.pandora.modular.movie.fragment.MovieFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/27.
 */
@Component(modules = MovieListModule.class)
public interface MovieComponent {
    void inject(MovieFragment fragment);
}
