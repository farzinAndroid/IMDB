package com.farzin.imdb.ui.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.utils.DateHelper
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.utils.MyDividerHorizontal

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchMediaItem(
    isMovie: Boolean = false,
    title: String,
    startYear: String = "",
    poster: String,
    onClick:()->Unit
) {

    MyDividerHorizontal(modifier = Modifier.padding(horizontal = 10.dp))


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Image(
            painter = rememberAsyncImagePainter(ImageHelper.appendImage(poster)),
            contentDescription = "",
            modifier = Modifier
                .height(100.dp)
                .width(70.dp)
                .padding(vertical = 8.dp)
                .padding(start = 8.dp, end = 6.dp),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .basicMarquee(
                        iterations = Int.MAX_VALUE,
                        animationMode = MarqueeAnimationMode.Immediately,
                        spacing = MarqueeSpacing(8.dp),
                        velocity = 20.dp,
                    )
            )


            Text(
                text = DateHelper.extractYearFromDate(startYear),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
            )

            if (!isMovie) {
                Text(
                    text = stringResource(R.string.tv),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                )

            } else {
                Text(
                    text = stringResource(R.string.movie),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                )
            }


        }

    }


}