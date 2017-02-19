package com.udemy.filmdemo.root;


import com.udemy.filmdemo.topmovies.TopMoviesActivity;
import com.udemy.filmdemo.topmovies.TopMoviesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chen on 2017/2/16.
 */

@Singleton
@Component(modules = {ApplicationModule.class, TopMoviesModule.class})
public interface ApplicationComponent {

    void inject(TopMoviesActivity target);
}
