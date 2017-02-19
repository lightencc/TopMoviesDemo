package com.udemy.filmdemo.http;

import java.io.IOException;
import java.net.HttpURLConnection;

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
public class ApiModuleForName {

    public final String BASE_URL = "http://api.douban.com/v2/movie/";
    //public final String API_KEY = "8551c026bbf22a4a386ebb5b87a5296b";

    @Provides
    public OkHttpClient provideClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();

//        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                HttpUrl url = request.url().newBuilder().addQueryParameter("api_key",API_KEY).build();
//                request = request.newBuilder().url(url).build();
//
//                return chain.proceed(request);
//            }
//        }).build();
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
    public MovieApiService provideApiServices(){
        return provideRetrofit(BASE_URL,provideClient()).create(MovieApiService.class);
    }
}
