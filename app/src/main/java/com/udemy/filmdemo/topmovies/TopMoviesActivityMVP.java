package com.udemy.filmdemo.topmovies;

import rx.Observable;

/**
 * Created by chen on 2017/2/17.
 */

public interface TopMoviesActivityMVP {

    interface View {

        void updateData(ViewModel viewModel);

        void showSnackbar(String s);
    }

    interface Presenter {

        void loadData();

        void rxUnsubscribe();

        void setView(TopMoviesActivityMVP.View view);
    }

    interface  Model {

        Observable<ViewModel> result();
    }
}
