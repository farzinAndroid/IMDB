package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.models.tvDetail.Genre
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.utils.ImageHelper

@Composable
fun MediaOverViewSection(
    genres:List<Genre>,
    posterPath:String,
    overView:String,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 6.dp, horizontal = 8.dp)
    ) {

        Image(
            painter = rememberAsyncImagePainter(ImageHelper.appendImage(posterPath)),
            contentDescription = "",
            modifier = Modifier
                .weight(0.3f)
                .height(170.dp)
                .padding(horizontal = 4.dp),
            contentScale = ContentScale.FillBounds
        )


        Column(
            modifier = Modifier
                .weight(0.7f)
                .padding(start = 4.dp),
            horizontalAlignment = Alignment.Start,
        ) {

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                items(genres, key = {it.id}) {
                    MediaDetailGenreItem(it.name) }
            }

            Text(
                text = overView,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.darkText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(top = 8.dp),
                lineHeight = 18.sp,
                maxLines = 10
            )

        }


    }

}