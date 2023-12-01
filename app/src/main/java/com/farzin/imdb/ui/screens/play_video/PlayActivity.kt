package com.farzin.imdb.ui.screens.play_video

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import com.farzin.imdb.R
import com.farzin.imdb.databinding.ActivityPlayBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class PlayActivity : ComponentActivity() {

    private lateinit var binding: ActivityPlayBinding
    private lateinit var youTubePlayer: YouTubePlayer
    private lateinit var key: String
    private var isFullScreen: Boolean = false
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (isFullScreen) {
                // if the player is in fullscreen, exit fullscreen
                youTubePlayer.toggleFullscreen()
            } else {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
       key = intent.getStringExtra("key").toString()




        onBackPressedDispatcher.addCallback(onBackPressedCallback)

        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.player)
        val fullscreenViewContainer = findViewById<FrameLayout>(R.id.full_screen_view_container)

        val iFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(1)
            .fullscreen(1) // enable full screen button
            .build()


        // we need to initialize manually in order to pass IFramePlayerOptions to the player
        youTubePlayerView.enableAutomaticInitialization = false


        youTubePlayerView.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                isFullScreen = true

                // the video will continue playing in fullscreenView
                youTubePlayerView.visibility = View.GONE
                fullscreenViewContainer.visibility = View.VISIBLE
                fullscreenViewContainer.addView(fullscreenView)

                // optionally request landscape orientation
                 requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onExitFullscreen() {
                isFullScreen = false

                // the video will continue playing in the player
                youTubePlayerView.visibility = View.VISIBLE
                fullscreenViewContainer.visibility = View.GONE
                fullscreenViewContainer.removeAllViews()
            }
        })

        binding.player.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@PlayActivity.youTubePlayer = youTubePlayer
                youTubePlayer.loadVideo(key, 0f)
            }
        }, iFramePlayerOptions)

        lifecycle.addObserver(youTubePlayerView)
    }

    }
