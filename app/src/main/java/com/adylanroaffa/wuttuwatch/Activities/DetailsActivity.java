package com.adylanroaffa.wuttuwatch.Activities;

import android.os.Bundle;
import android.view.View;

import com.adylanroaffa.wuttuwatch.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class DetailsActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        android.widget.Toolbar toolbar = (android.widget.Toolbar) findViewById(R.id.detail_toolbar);
        toolbar.setTitle("");
        setActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.detail_youtube);

        youTubePlayerView.initialize("AIzaSyC297LVsg5_ghU4UeUxX9W4dqfLTT4kYKQ", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo("-XMaWpGW0yI");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
        //TODO : Add Youtube Player View and movie overview (both xml and program)
    }


}
