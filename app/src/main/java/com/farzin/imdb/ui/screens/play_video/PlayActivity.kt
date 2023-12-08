package com.farzin.imdb.ui.screens.play_video

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.NonNull
import com.farzin.imdb.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class PlayActivity : ComponentActivity() {

    private lateinit var key: String
    private lateinit var youTubePlayerView: YouTubePlayerView

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val intent = intent
        key = intent.getStringExtra("key").toString()

        youTubePlayerView = findViewById(R.id.youtube_player_view)

        initYouTubePlayerView()

    }


    override fun onConfigurationChanged(@NonNull newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.matchParent()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            youTubePlayerView.wrapContent()
        }
    }

    private fun initYouTubePlayerView() {
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(key,0f)
            }
        })
    }


}
