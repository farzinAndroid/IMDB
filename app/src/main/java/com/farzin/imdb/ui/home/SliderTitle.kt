package com.farzin.imdb.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.normalText
import com.farzin.imdb.ui.theme.starColor
import com.farzin.imdb.utils.MySpacerWidth

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SliderTitle(
    title:String,
    votesAverage:String
) {


    Column(
        modifier = Modifier
            .padding(start = 20.dp)
            .width(150.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.normalText,
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
                color = MaterialTheme.colorScheme.normalText
            )

        }


    }

}