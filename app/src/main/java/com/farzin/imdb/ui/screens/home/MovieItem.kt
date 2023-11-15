package com.farzin.imdb.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.DateHelper
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.utils.MySpacerWidth


@Composable
fun MovieItem(
    onAddButtonClicked: () -> Unit = {},
    onCardClicked: () -> Unit = {},
    isFromWatchlist: Boolean = false,
    posterPath:String,
    voteAverage:Double,
    name:String,
    releaseDate:String
) {


    MySpacerWidth(width = 10.dp)

    Card(
        modifier = Modifier
            .width(150.dp)
            .height(370.dp)
            .clickable { onCardClicked() },
        shape = Shapes().small,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.sectionContainerBackground),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f),
                contentAlignment = Alignment.TopStart
            ) {

                Image(
                    painter = rememberAsyncImagePainter(ImageHelper.appendImage(posterPath)),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                SaveButton(
                    icon =
                    if (isFromWatchlist)
                        painterResource(R.drawable.star_fill)
                    else
                        painterResource(R.drawable.add)
                ) {
                    onAddButtonClicked()
                }
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .padding(horizontal = 4.dp)
            ) {

                Icon(
                    painter = painterResource(R.drawable.star_fill),
                    contentDescription = "",
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.imdbYellow
                )

                MySpacerWidth(width = 8.dp)

                Text(
                    text = String.format("%.1f", voteAverage),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Thin,
                    color = MaterialTheme.colorScheme.darkText
                )


            }


            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .padding(horizontal = 4.dp)
                    .padding(top = 4.dp),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.darkText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            MySpacerHeight(height = 10.dp)

            Text(
                text = DateHelper.extractYearFromDate(releaseDate),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .padding(horizontal = 4.dp),
                textAlign = TextAlign.Start,
                color = Color.Gray
            )


        }

    }

}