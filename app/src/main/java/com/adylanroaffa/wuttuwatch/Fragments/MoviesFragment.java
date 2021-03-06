package com.adylanroaffa.wuttuwatch.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adylanroaffa.wuttuwatch.Adapters.HomescreenMovieAdapter;
import com.adylanroaffa.wuttuwatch.Movie;
import com.adylanroaffa.wuttuwatch.MoviesResponse;
import com.adylanroaffa.wuttuwatch.R;
import com.adylanroaffa.wuttuwatch.REST.ApiClient;
import com.adylanroaffa.wuttuwatch.REST.ApiInterface;
import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class MoviesFragment extends Fragment{

    // TODO: Rename and change types of parameters

    private RecyclerView recyclerView;
    private HomescreenMovieAdapter homescreenMovieAdapter;
    private LinearLayoutManager llm;
    private List<Movie> movies;
    private ProgressBar progressBar;


    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get date from 7 days ago
        DateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        long now = date.getTime();
        long sevenDaysAgo = now - (7*24*60*60*1000);
        Date thisWeekDate = new Date(sevenDaysAgo);
        String thisWeek = sm.format(thisWeekDate);

        //show this week movies
        loadSortedMovie(ApiClient.POPULARITY_DESC,thisWeek);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.movie_progress_bar);

                recyclerView = (RecyclerView) view.findViewById(R.id.popular_recycler_view);

        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);

        return view;

    }

    private void initializeAdapter(){

        homescreenMovieAdapter = new HomescreenMovieAdapter(Glide.with(this),movies);
        recyclerView.setAdapter(homescreenMovieAdapter);
    }

    private void loadSortedMovie(String sortBy,String releaseDate){
        try {
            ApiClient apiClient = new ApiClient();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<MoviesResponse> call = apiInterface.getSortedMovies(ApiClient.API_KEY,sortBy,releaseDate);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    initializeAdapter();
                    progressBar.setVisibility(GONE);
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getActivity(), "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}


