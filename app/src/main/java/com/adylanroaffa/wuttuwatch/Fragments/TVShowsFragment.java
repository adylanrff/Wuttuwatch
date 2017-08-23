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

import com.adylanroaffa.wuttuwatch.Adapters.HomescreenTVShowAdapter;
import com.adylanroaffa.wuttuwatch.R;
import com.adylanroaffa.wuttuwatch.REST.ApiClient;
import com.adylanroaffa.wuttuwatch.REST.ApiInterface;
import com.adylanroaffa.wuttuwatch.TVShow;
import com.adylanroaffa.wuttuwatch.TVShowsResponse;
import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowsFragment extends Fragment {

    // TODO: Rename and change types of parameters

    private RecyclerView recyclerView;
    private HomescreenTVShowAdapter homescreenTVShowAdapter;
    private LinearLayoutManager llm;
    private List<TVShow> tvShows ;
    private String thisweek;
    private ProgressBar progressBar;

    public TVShowsFragment() {
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
        thisweek = sm.format(thisWeekDate);

        //show this week movies
        loadSortedTV(ApiClient.POPULARITY_DESC,thisweek);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.movie_progress_bar);


        recyclerView = (RecyclerView) view.findViewById(R.id.popular_recycler_view);
        recyclerView.setHasFixedSize(true);

        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);

        return view;

    }

    private void initializeAdapter(){
        homescreenTVShowAdapter = new HomescreenTVShowAdapter(Glide.with(this),tvShows);
        recyclerView.setAdapter(homescreenTVShowAdapter);
    }


    private void loadSortedTV(String sortBy,String releaseDate){
        try {
            ApiClient apiClient = new ApiClient();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<TVShowsResponse> call = apiInterface.getSortedTV(ApiClient.API_KEY,sortBy,releaseDate);
            call.enqueue(new Callback<TVShowsResponse>() {
                @Override
                public void onResponse(Call<TVShowsResponse> call, Response<TVShowsResponse> response) {
                    tvShows = response.body().getResults();
                    initializeAdapter();
                    progressBar.setVisibility(GONE);
                }

                @Override
                public void onFailure(Call<TVShowsResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getActivity(), "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

