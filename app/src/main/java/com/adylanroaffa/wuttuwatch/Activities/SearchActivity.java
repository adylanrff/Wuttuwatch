package com.adylanroaffa.wuttuwatch.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.adylanroaffa.wuttuwatch.Adapters.SearchMovieAdapter;
import com.adylanroaffa.wuttuwatch.Adapters.SearchTVShowAdapter;
import com.adylanroaffa.wuttuwatch.Movie;
import com.adylanroaffa.wuttuwatch.MoviesResponse;
import com.adylanroaffa.wuttuwatch.R;
import com.adylanroaffa.wuttuwatch.REST.ApiClient;
import com.adylanroaffa.wuttuwatch.REST.ApiInterface;
import com.adylanroaffa.wuttuwatch.TVShow;
import com.adylanroaffa.wuttuwatch.TVShowsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private List<Movie> movies;
    private List<TVShow> tvShows;
    private LinearLayoutManager llm;
    private ContentLoadingProgressBar progressBar;
    private SearchMovieAdapter searchMovieAdapter;
    private SearchTVShowAdapter searchTVShowAdapter;
    private RecyclerView recyclerView;
    private String screenplayType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        Add toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_screen_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle(R.string.action_search);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        progressBar = (ContentLoadingProgressBar) findViewById(R.id.search_progress_bar);
        final Spinner spinner = (Spinner) findViewById(R.id.search_spinner);

        recyclerView = (RecyclerView) findViewById(R.id.search_recycler_view);
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

//      add search edit text functionality
        final EditText searchEditText = (EditText) findViewById(R.id.search_edit_text);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    String query = searchEditText.getText().toString();
                    screenplayType = spinner.getSelectedItem().toString();
                    doSearching(screenplayType,query);


                    Toast.makeText(SearchActivity.this,"DO SEARCHING",Toast.LENGTH_SHORT)
                            .show();

                    //HIDE SOFT KEYBOARD FROM THE SCREEN
                    InputMethodManager imm = (InputMethodManager) SearchActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                }
                return true;
            }
        });

        //Recycler view





        //TODO : get JSON object and parse

    }


    private void initializeMovieAdapter(){
        searchMovieAdapter = new SearchMovieAdapter(movies);
        recyclerView.setAdapter(searchMovieAdapter);
    }
    private void initializeTVShowAdapter(){
        searchTVShowAdapter = new SearchTVShowAdapter(tvShows);
        recyclerView.setAdapter(searchTVShowAdapter);
    }

    private void doSearching(String type,String query){

        progressBar.show();
        if (type.equalsIgnoreCase("movies")){
            try {
                ApiClient apiClient = new ApiClient();
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<MoviesResponse> call = apiInterface.searchMovie(ApiClient.API_KEY,query);

                call.enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        movies = response.body().getResults();
                        initializeMovieAdapter();
                        progressBar.hide();
                        Toast.makeText(getApplicationContext(),screenplayType,Toast.LENGTH_SHORT).show();
                        searchMovieAdapter.SetOnItemClickListener(new SearchMovieAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position, String id) {
                                Intent i = new Intent(SearchActivity.this,DetailsActivity.class);
                                startActivity(i);
                            }

                        });

                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                        Toast.makeText(getApplicationContext(),"Error fetching data!",Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (Exception e){
                Log.d("Error", e.getMessage());
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        else{
            try {
                ApiClient apiClient = new ApiClient();
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<TVShowsResponse> call = apiInterface.searchTV(ApiClient.API_KEY,query);
                call.enqueue(new Callback<TVShowsResponse>() {
                    @Override
                    public void onResponse(Call<TVShowsResponse> call, Response<TVShowsResponse> response) {
                        tvShows = response.body().getResults();
                        initializeTVShowAdapter();
                        progressBar.hide();
                        searchTVShowAdapter.SetOnItemClickListener(new SearchTVShowAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position, String id) {
                                Intent i = new Intent(SearchActivity.this,DetailsActivity.class);
                                startActivity(i);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<TVShowsResponse> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                        Toast.makeText(getApplicationContext(),"Error fetching data!",Toast.LENGTH_SHORT);
                    }
                });

            } catch (Exception e){
                Log.d("Error", e.getMessage());
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}

