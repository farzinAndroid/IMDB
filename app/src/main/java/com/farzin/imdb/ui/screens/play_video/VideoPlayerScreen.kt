package com.farzin.imdb.ui.screens.play_video

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController

// not used
@Composable
fun VideoPlayerScreen(key:String,navController: NavController) {

    val exoPlayer : ExoPlayer
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
        
    }



}