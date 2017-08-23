package com.adylanroaffa.wuttuwatch.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adylanroaffa.wuttuwatch.Movie;
import com.adylanroaffa.wuttuwatch.R;
import com.bumptech.glide.RequestManager;

import java.util.List;

/**
 * Created by Adylan Roaffa on 8/14/2017.
 */

public class HomescreenMovieAdapter extends RecyclerView.Adapter<HomescreenMovieAdapter.ViewHolder>
{

    private List<Movie> movies;
    private String imageURL;
    private RequestManager glide;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;


        public ViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.popular_title_tv);
            image = (ImageView) v.findViewById(R.id.popular_image_view);
        }
    }

    public HomescreenMovieAdapter(RequestManager glide, List<Movie> movies) {
        this.glide = glide;
        this.movies=movies;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(HomescreenMovieAdapter.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());
;


        //TODO : from json, use imageLoader to load image from url
        glide.load(movie.getPosterPath())
                .centerCrop()
                .placeholder(R.drawable.ic_image_placeholder)
                .into(holder.image);

    }


    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public HomescreenMovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
