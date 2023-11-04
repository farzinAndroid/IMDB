package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.utils.ImageHelper

@Composable
fun ImageItem(
    path:String
) {


    Card(
        modifier = Modifier
            .height(150.dp)
            .width(200.dp)
            .padding(horizontal = 10.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = Shapes().small
    ) {

        Image(
            painter = rememberAsyncImagePainter(ImageHelper.appendImage(path)),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }

}