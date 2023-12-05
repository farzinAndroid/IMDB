package com.farzin.imdb.ui.screens.episode_guide

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.strongGray
import com.farzin.imdb.utils.DateHelper
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.utils.MyDividerHorizontal
import com.farzin.imdb.utils.MySpacerWidth

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EpisodeItem(
    airDate: String,
    posterPath: String,
    vote: Double,
    name: String,
    overView: String,
) {

    MyDividerHorizontal(modifier = Modifier.padding(horizontal = 10.dp))


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { },
        horizontalArrangement = Arrangement.Start
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                ImageHelper.appendImage(posterPath),
                imageLoader = ImageLoader.Builder(LocalContext.current)
                    .crossfade(true)
                    .crossfade(500)
                    .build(),
                contentScale = ContentScale.FillBounds
            ),
            contentDescription = "",
            modifier = Modifier
                .height(160.dp)
                .width(100.dp)
                .padding(vertical = 8.dp)
                .padding(start = 8.dp, end = 6.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {

            Text(
                text = name,
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


            Row(verticalAlignment = Alignment.CenterVertically) {

                if (vote != 0.0) {
                    Icon(
                        painter = painterResource(R.drawable.star_fill),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.imdbYellow,
                        modifier = Modifier.size(16.dp)
                    )

                    Text(
                        text = String.format("%.1f", vote),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.strongGray,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 4.dp)
                    )


                    MySpacerWidth(width = 16.dp)

                }

                if (airDate != ""){
                    Text(
                        text = DateHelper.formatSimpleDate(airDate),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.strongGray,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }



            }


            Text(
                text = overView,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.strongGray,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(start = 4.dp)
            )


        }

    }

}