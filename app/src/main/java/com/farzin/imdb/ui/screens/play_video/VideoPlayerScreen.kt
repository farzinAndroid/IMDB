package com.farzin.imdb.ui.screens.play_video

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun VideoPlayerScreen(key: String, navController: NavController) {

    val ctx = LocalContext.current
    val coroutine = rememberCoroutineScope()

    AndroidView(factory = {
        var view = YouTubePlayerView(it)
        view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)

                        youTubePlayer.loadVideo(key, 0f)


                }
            }
        )
        view
    })


}


/* val exoPlayer : ExoPlayer
    val ctx = LocalContext.current

    exoPlayer = ExoPlayer.Builder(ctx).build()
    val mediaItem = MediaItem.fromUri("")

    exoPlayer.setMediaItem(mediaItem)
    exoPlayer.prepare()
    exoPlayer.playWhenReady

    Box(modifier = Modifier.fillMaxSize()){
        DisposableEffect(Unit){
            onDispose { exoPlayer.release() }
        }


        AndroidView(factory = {
            PlayerView(ctx).apply {
                player = exoPlayer
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
            }
        })

    }*/