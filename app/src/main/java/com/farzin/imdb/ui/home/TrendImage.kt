package com.farzin.imdb.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.utils.ImageHelper

@Composable
fun TrendImage(backDropPath:String) {


    Box(
        modifier = Modifier
            .height(150.dp)
            .width(120.dp)
            .padding(start = 20.dp)
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.TopStart
    ){

        Image(
            painter = rememberAsyncImagePainter(ImageHelper.appendImage(backDropPath)),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        SaveButton(
            onClick = {}
        )

    }

}