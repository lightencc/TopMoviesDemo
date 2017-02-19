package com.udemy.filmdemo.topmovies;

import com.udemy.filmdemo.http.apimodel.Subject;

import rx.Observable;

/**
 * Created by chen on 2017/2/18.
 */

public interface Repository {

    public Observable<Subject> getResultsFromMemory();

    public Observable<Subject> getResultsFormNetwork();

    public Observable<String> getCountriesFromMemory();

    public Observable<String> getCountriesFromNetwork();

    public Observable<Subject> getResultData();

    public Observable<String> getCountryData();
}
