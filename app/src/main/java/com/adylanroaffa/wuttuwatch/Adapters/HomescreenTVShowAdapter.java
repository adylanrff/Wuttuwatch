package com.adylanroaffa.wuttuwatch.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adylanroaffa.wuttuwatch.R;
import com.adylanroaffa.wuttuwatch.TVShow;
import com.bumptech.glide.RequestManager;

import java.util.List;

/**
 * Created by Adylan Roaffa on 8/14/2017.
 */

public class HomescreenTVShowAdapter extends RecyclerView.Adapter<HomescreenTVShowAdapter.ViewHolder>
{
    private RequestManager glide;
    private List<TVShow> tvShows;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;


        public ViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.popular_title_tv);
            image = (ImageView) v.findViewById(R.id.popular_image_view);
        }
    }

    public HomescreenTVShowAdapter(RequestManager glide, List<TVShow> tvShows) {
        this.glide = glide;
        this.tvShows=tvShows;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(HomescreenTVShowAdapter.ViewHolder holder, int position) {
        TVShow tvShow = tvShows.get(position);
        holder.title.setText(tvShow.getTitle());
        //TODO : from json, use imageLoader to load image from url

        glide.load(tvShow.getPosterPath())
                .centerCrop()
                .placeholder(R.drawable.ic_image_placeholder)
                .into(holder.image);

    }

    public List<TVShow> getTvShows() {
        return tvShows;
    }

    @Override
    public HomescreenTVShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

}
