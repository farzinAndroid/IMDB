package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.utils.MySpacerWidth

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CastCardItem(
    name:String,
    profilePath:String,
    character:String
) {


    MySpacerWidth(width = 10.dp)

    Card(
        modifier = Modifier
            .width(150.dp)
            .height(350.dp)
            .clickable { },
        shape = Shapes().small,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.sectionContainerBackground),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {

            Image(
                painter = rememberAsyncImagePainter(ImageHelper.appendImage(profilePath)),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f),
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = name,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.darkText,
                modifier = Modifier
                    .weight(0.3f)
                    .padding(start = 4.dp)
                    .padding(top = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = character,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier
                    .basicMarquee(
                        iterations = Int.MAX_VALUE,
                        animationMode = MarqueeAnimationMode.Immediately,
                        spacing = MarqueeSpacing(8.dp),
                        velocity = 20.dp,
                    )
                    .weight(0.1f)
                    .padding(start = 4.dp)
                    .padding(top = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }

}