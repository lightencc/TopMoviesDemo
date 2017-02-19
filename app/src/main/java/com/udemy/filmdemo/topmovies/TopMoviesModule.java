package com.udemy.filmdemo.topmovies;

import com.udemy.filmdemo.http.ApiModuleForInfo;
import com.udemy.filmdemo.http.ApiModuleForName;
import com.udemy.filmdemo.http.MoreInfoApiService;
import com.udemy.filmdemo.http.MovieApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chen on 2017/2/18.
 */
@Module(includes = {ApiModuleForName.class, ApiModuleForInfo.class})
public class TopMoviesModule {

    @Provides
    public TopMoviesActivityMVP.Presenter provideTopMoviesActivityPresenter(TopMoviesActivityMVP.Model topMoviesModel){
        return new TopMoviesPresenter(topMoviesModel);
    }

    @Provides
    public TopMoviesActivityMVP.Model provideTopMoviesActivityModel(Repository repository){
        return new TopMoviesModel(repository);
    }

    @Provides
    public Repository provideRepo(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService){
        return new TopMoviesRepository(movieApiService,moreInfoApiService);
    }
}
