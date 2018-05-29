package com.tv.demo.myapp.api.client;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Krot on 5/28/18.
 */

public class TheMovieDBApiClient {

    public static final String API_BASE_URL = "https://api.themoviedb.org/3/";
    public static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w300";
    public static final String NOW_PLAYING = "movie/now_playing";
    public static final String TOP_RATED = "movie/top_rated";
    public static final String POPULAR = "movie/popular";
    public static final String UP_COMING = "movie/upcoming";

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }


}
