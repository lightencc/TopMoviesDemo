package com.udemy.filmdemo.topmovies;

import com.udemy.filmdemo.http.apimodel.Subject;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by chen on 2017/2/18.
 */

public class TopMoviesModel implements TopMoviesActivityMVP.Model {

    private Repository repository;

    public TopMoviesModel(Repository repository){
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result() {

        return Observable.zip(repository.getResultData(), repository.getCountryData(), new Func2<Subject, String, ViewModel>() {
            @Override
            public ViewModel call(Subject subject, String s) {
                return new ViewModel(subject.getTitle(),s);
            }
        });
    }
}
