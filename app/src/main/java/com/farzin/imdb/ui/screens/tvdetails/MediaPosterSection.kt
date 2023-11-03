package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.utils.DateHelper
import com.farzin.imdb.utils.ImageHelper

@Composable
fun MediaPosterSection(
    picturePath: String,
    startYear: String = "",
    endYear: String = "",
    isMovie: Boolean,
    name: String,
) {

    val startYearText = DateHelper.extractYearFromDate(startYear)
    val endYearText = DateHelper.extractYearFromDate(endYear)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(top = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {



        Image(
            painter = rememberAsyncImagePainter(ImageHelper.appendImage(picturePath)),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Black,
                        )
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold
            )

            if (!isMovie) {
                Text(
                    text = "($startYearText-$endYearText)",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                Text(
                    text = "($startYear)",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }




    }


}