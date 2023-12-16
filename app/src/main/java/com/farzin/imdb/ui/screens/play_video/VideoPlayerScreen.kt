package com.farzin.imdb.ui.screens.play_video

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.SimpleYouTubePlayerOptionsBuilder
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubePlayer
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubeVideoId
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.model.YouTubeEvent
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.model.YouTubeExecCommand


@SuppressLint("UnrememberedMutableState")
@Composable
fun VideoPlayerScreen(key: String, navController: NavController) {

    var execCommand by remember {mutableStateOf<YouTubeExecCommand?>(null)}

    YouTubePlayer(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        options = SimpleYouTubePlayerOptionsBuilder.builder {
            autoplay(true) // autoplay = true
            controls(true).rel(false) // controls = false, rel = false
            ivLoadPolicy(true)
            ccLoadPolicy(true)
            fullscreen = true
        },
        execCommand = execCommand,
        actionListener = {action->
            when (action) {
                YouTubeEvent.Ready -> {
                    execCommand = YouTubeExecCommand.LoadVideo(
                        videoId = YouTubeVideoId(key),
                    )
                }

                is YouTubeEvent.VideoDuration -> {
                }

                is YouTubeEvent.TimeChanged -> {
                }

                is YouTubeEvent.OnVideoIdHandled,
                is YouTubeEvent.Error,
                is YouTubeEvent.PlaybackQualityChange,
                is YouTubeEvent.RateChange,
                is YouTubeEvent.StateChanged,
                -> println("webViewState. onAction HANDlED: $action")
            }
        },
    )
}