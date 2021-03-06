package com.sc.bigboss.bigboss;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class Youtube extends YouTubeBaseActivity {

    private Button play;

    private YouTubePlayerView youTubePlayerView;

    private YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        youTubePlayerView = findViewById(R.id.videoplayer);

        play = findViewById(R.id.play);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo("7lWeQs8Firo");

                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };


        play.setOnClickListener(v -> youTubePlayerView.initialize(PlayerConfig.API_KEY, onInitializedListener));

    }

    class PlayerConfig {

        PlayerConfig() {
        }

        static final String API_KEY =
                "AIzaSyCB_Qx_NUNn1YL-jMoXZfG4j8xJDAOtlBo";
    }
}
