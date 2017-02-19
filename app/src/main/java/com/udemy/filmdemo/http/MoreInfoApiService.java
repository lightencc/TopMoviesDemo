package com.udemy.filmdemo.http;

import com.udemy.filmdemo.http.apimodel.MoreInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chen on 2017/2/18.
 */

public interface MoreInfoApiService {

    @GET("subject/{id}")
    Observable<MoreInfo> getCountry(@Path("id") String id);
}
