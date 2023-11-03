package com.farzin.imdb.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.starColor
import com.farzin.imdb.utils.MySpacerWidth
import com.farzin.imdb.utils.ImageHelper

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PosterImage(
    backDropPath: String,
    title:String,
    votesAverage:String,
    onClick: () -> Unit,
) {

    Row(
        verticalAlignment = Alignment.Bottom
    ) {

        Box(
            modifier = Modifier
                .height(200.dp)
                .width(140.dp)
                .padding(start = 20.dp)
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.TopStart
        ) {

            Image(
                painter = rememberAsyncImagePainter(ImageHelper.appendImage(backDropPath)),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            SaveButton(
                onClick = { onClick() },
                icon = painterResource(R.drawable.add)
            )

        }

        Column(
            modifier = Modifier
                .width(150.dp)
                .wrapContentHeight()
                .padding(start = 16.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.darkText,
                modifier = Modifier
                    .align(Alignment.Start)
                    .wrapContentWidth()
                    .basicMarquee(
                        iterations = Int.MAX_VALUE,
                        animationMode = MarqueeAnimationMode.Immediately,
                        spacing = MarqueeSpacing(8.dp),
                        velocity = 20.dp,
                    ),
                maxLines = 1,
                textAlign = TextAlign.Start
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    painter = painterResource(R.drawable.star_fill),
                    contentDescription = "",
                    modifier = Modifier
                        .size(16.dp),
                    tint = MaterialTheme.colorScheme.starColor
                )

                MySpacerWidth(width = 8.dp)

                Text(
                    text = votesAverage,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Thin,
                    color = MaterialTheme.colorScheme.darkText
                )

            }


        }

    }



}