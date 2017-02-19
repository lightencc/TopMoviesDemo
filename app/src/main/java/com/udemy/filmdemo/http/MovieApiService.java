package com.udemy.filmdemo.http;

import com.udemy.filmdemo.http.apimodel.TopRated;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chen on 2017/2/18.
 */

public interface MovieApiService {

    @GET("top250")
    Observable<TopRated> getTopRatedMovies(@Query("start") Integer startIndex, @Query("count") Integer offset);
}
