package com.udemy.filmdemo.http;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chen on 2017/2/18.
 */
@Module
public class ApiModuleForInfo {

    public final String BASE_URL = "http://api.douban.com/v2/movie/";

    @Provides
    public OkHttpClient provideClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();

    }

    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public MoreInfoApiService provideApiServices(){
        return provideRetrofit(BASE_URL,provideClient()).create(MoreInfoApiService.class);
    }
}
