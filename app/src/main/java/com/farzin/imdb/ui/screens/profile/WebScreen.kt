package com.farzin.imdb.ui.screens.profile

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.farzin.imdb.utils.Constants

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebScreen() {



    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.userAgentString = System.getProperty("http.agent")
            loadUrl(Constants.TMDB_WEBSITE)
        }
    }, update = {
        it.loadUrl(Constants.TMDB_WEBSITE)
    })


}