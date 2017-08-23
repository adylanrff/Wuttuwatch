package com.adylanroaffa.wuttuwatch.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adylanroaffa.wuttuwatch.Movie;
import com.adylanroaffa.wuttuwatch.R;

import java.util.List;

/**
 * Created by Adylan Roaffa on 8/14/2017.
 */

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.ViewHolder> {

    private List<Movie> movies;
    static OnItemClickListener mItemClickListener;

    public SearchMovieAdapter(List<Movie> movies) {
        this.movies=movies;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public ImageView image;
        public TextView rating;
        public TextView genre;

        public ViewHolder(View v) {
            super(v);
            rating = (TextView) v.findViewById(R.id.search_rating);
            genre = (TextView) v.findViewById(R.id.search_genre);
            title = (TextView) v.findViewById(R.id.search_title);
            image = (ImageView) v.findViewById(R.id.search_result_image_view);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            String id = "Test";
            mItemClickListener.onItemClick(v,getAdapterPosition(),id);
        }
    }

        public interface OnItemClickListener{
            public void onItemClick(View view, int position, String id);
        }

        public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
            this.mItemClickListener = mItemClickListener;
        }

    @Override
    public SearchMovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_item,parent,false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movie movie = movies.get(position);

        holder.title.setText(movie.getTitle());
        //holder.genre.setText(movie.getGenreIDs());
        holder.rating.setText(""+movie.getVoteAverage());
        //TODO : from json, use imageLoader to load image from url
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
