package com.adylanroaffa.wuttuwatch.REST;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Adylan Roaffa on 8/21/2017.
 */

public class ApiClient {


    public final static String API_KEY = "ba52da2ef25cc2d191cd5d962edf50ba";
    public final static String IMAGE_URL = "https://image.tmdb.org/t/p/";
    public final static String W154_IMAGE_SIZE = "w154";


    public final static String POPULARITY_DESC= "popularity.desc";
    public final static String TOP_RATED_DESC= "popularity.desc";


    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
