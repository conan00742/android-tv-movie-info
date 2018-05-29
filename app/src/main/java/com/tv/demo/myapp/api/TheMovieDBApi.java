package com.tv.demo.myapp.api;

import com.tv.demo.myapp.api.client.TheMovieDBApiClient;
import com.tv.demo.myapp.model.MovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Krot on 5/28/18.
 */

public interface TheMovieDBApi {


    @GET(TheMovieDBApiClient.NOW_PLAYING)
    Single<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey,
                                              @Query("page") int page);

    @GET(TheMovieDBApiClient.POPULAR)
    Single<MovieResponse> getPopularMovies(@Query("api_key") String apiKey,
                                           @Query("page") int page);

    @GET(TheMovieDBApiClient.TOP_RATED)
    Single<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey,
                                            @Query("page") int page);

    @GET(TheMovieDBApiClient.UP_COMING)
    Single<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey,
                                            @Query("page") int page);

}
