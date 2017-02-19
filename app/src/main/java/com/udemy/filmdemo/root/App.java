package com.udemy.filmdemo.root;

import android.app.Application;

import com.udemy.filmdemo.topmovies.TopMoviesModule;


/**
 * Created by chen on 2017/2/16.
 */

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .topMoviesModule(new TopMoviesModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
