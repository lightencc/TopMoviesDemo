package com.udemy.filmdemo.topmovies;

import com.udemy.filmdemo.http.MoreInfoApiService;
import com.udemy.filmdemo.http.MovieApiService;
import com.udemy.filmdemo.http.apimodel.MoreInfo;
import com.udemy.filmdemo.http.apimodel.Subject;
import com.udemy.filmdemo.http.apimodel.TopRated;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by chen on 2017/2/18.
 */

public class TopMoviesRepository implements Repository {

    private MovieApiService movieApiService;
    private MoreInfoApiService moreInfoApiService;
    private List<String> countries;
    private List<Subject> results;
    private long timestamp;

    private static final long STALE_MS = 20 * 1000; // Data is stale after 20 seconds

    public TopMoviesRepository(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService){
        this.moreInfoApiService = moreInfoApiService;
        this.timestamp = System.currentTimeMillis();
        this.movieApiService = movieApiService;
        countries = new ArrayList<>();
        results = new ArrayList<>();
    }

    public boolean isUpToDate(){
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    @Override
    public Observable<Subject> getResultsFromMemory() {

        if(isUpToDate()){
            return Observable.from(results);
        }else{
            timestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();

        }
    }

    @Override
    public Observable<Subject> getResultsFormNetwork() {

//        Observable<TopRated> topRatedObservable = movieApiService.getTopRatedMovies(0,10)
//                                                    .concatWith(movieApiService.getTopRatedMovies(10,10))
//                                                    .concatWith(movieApiService.getTopRatedMovies(20,10));
//        return topRatedObservable.concatMap(new Func1<TopRated, Observable<Subject>>() {
//            @Override
//            public Observable<Subject> call(TopRated topRated) {
//                return Observable.from(topRated.getSubjects());
//            }
//        })
        return movieApiService.getTopRatedMovies(0,30)
                .concatMap(new Func1<TopRated, Observable<Subject>>() {
                    @Override
                    public Observable<Subject> call(TopRated topRated) {
                        return Observable.from(topRated.getSubjects());
                    }
                })
         .doOnNext(new Action1<Subject>() {
            @Override
            public void call(Subject result) {
                results.add(result);
            }
        });
    }

    @Override
    public Observable<String> getCountriesFromMemory() {
        if(isUpToDate()) {
            return Observable.from(countries);
        }else{
            timestamp = System.currentTimeMillis();
            countries.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountriesFromNetwork() {
        return getResultsFormNetwork().concatMap(new Func1<Subject, Observable<MoreInfo>>() {
            @Override
            public Observable<MoreInfo> call(Subject subject) {
                return moreInfoApiService.getCountry(subject.getId());
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends MoreInfo>>() {
            @Override
            public Observable<? extends MoreInfo> call(Throwable throwable) {
                MoreInfo moreInfo = new MoreInfo();
                List<String> countriesArray = new ArrayList<String>();
                countriesArray.add("暂无信息");
                moreInfo.setCountries(countriesArray);
                return Observable.just(moreInfo);
            }
        }).concatMap(new Func1<MoreInfo, Observable<String>>() {
            @Override
            public Observable<String> call(MoreInfo moreInfo) {
                List<String> countriesArray = moreInfo.getCountries();
                String countriesDesc ="";
                for(String countryEntry : countriesArray){
                    countriesDesc += countryEntry;
                    countriesDesc += ",";
                }
                countriesDesc = countriesDesc.substring(0,countriesDesc.length()-1);
                return Observable.just(countriesDesc);
            }
        }).doOnNext(new Action1<String>() {
            @Override
            public void call(String countriesDesc) {
                countries.add(countriesDesc);
            }
        });
    }

    @Override
    public Observable<Subject> getResultData() {
        return getResultsFromMemory().switchIfEmpty(getResultsFormNetwork());
    }

    @Override
    public Observable<String> getCountryData() {
        return getCountriesFromMemory().switchIfEmpty(getCountriesFromNetwork());
    }
}
