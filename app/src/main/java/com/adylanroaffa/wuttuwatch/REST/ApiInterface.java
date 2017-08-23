package com.adylanroaffa.wuttuwatch.REST;

import com.adylanroaffa.wuttuwatch.MoviesResponse;
import com.adylanroaffa.wuttuwatch.TVShowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Adylan Roaffa on 8/21/2017.
 */

public interface ApiInterface {


    @GET("discover/movie")
    Call<MoviesResponse> getSortedMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortBy, @Query("primary_release_date.gte") String releaseDate);

    @GET("discover/tv")
    Call<TVShowsResponse> getSortedTV(@Query("api_key") String apiKey, @Query("sort_by") String sortBy, @Query("primary_release_date.gte") String releaseDate);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Query("api_key") String apiKey);

    @GET("tv/{id}")
    Call<TVShowsResponse> getTVDetails(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MoviesResponse> searchMovie(@Query("api_key") String apiKey,@Query("query") String keyword);

    @GET("search/tv")
    Call<TVShowsResponse> searchTV(@Query("api_key") String apiKey,@Query("query") String keyword);

    



}
