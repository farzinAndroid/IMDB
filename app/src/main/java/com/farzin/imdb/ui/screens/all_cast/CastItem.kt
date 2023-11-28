package com.farzin.imdb.ui.screens.all_cast

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.strongGray
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.utils.MyDividerHorizontal
import com.farzin.imdb.utils.MySpacerHeight

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CastItem(
    name: String,
    profilePath: String,
    character: String,
    numberOfEpisode: Int = 0,
    onCardClicked: () -> Unit,
) {


    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onCardClicked() }) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Image(
                painter = rememberAsyncImagePainter(ImageHelper.appendImage(profilePath)),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight()
                    .padding(vertical = 4.dp)
                    .padding(horizontal = 8.dp),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(0.7f)
            ) {
                Text(
                    text = name,
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.darkText
                )

                MySpacerHeight(height = 6.dp)

                Text(
                    text = character,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .basicMarquee(
                            iterations = Int.MAX_VALUE,
                            animationMode = MarqueeAnimationMode.Immediately,
                            spacing = MarqueeSpacing(8.dp),
                            velocity = 20.dp,
                        ),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.strongGray
                )

                if (numberOfEpisode != 0){
                    MySpacerHeight(height = 6.dp)

                    Text(
                        text = "($numberOfEpisode ${stringResource(R.string.episodes)})",
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.strongGray
                    )
                }

            }

        }

        MyDividerHorizontal()

    }


}