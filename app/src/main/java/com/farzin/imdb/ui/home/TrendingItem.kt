package com.farzin.imdb.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.R
import com.farzin.imdb.models.home.Result
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.utils.ImageHelper.appendImage

@Composable
fun TrendingItem(item: Result) {




        Image(
            painter = rememberAsyncImagePainter(appendImage(item.backdrop_path)),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )


//        TrendImage(backDropPath = item.backdrop_path)





}