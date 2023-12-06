package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.models.tvDetail.TVReviewModelResult
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.DigitHelper
import com.farzin.imdb.utils.MySpacerWidth

@Composable
fun CommentCard(
    item:TVReviewModelResult,
    onClick:()->Unit
) {
    MySpacerWidth(width = 8.dp)

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .width(300.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = Shapes().small,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.sectionContainerBackground)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = item.author_details.username,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.darkText,
                    modifier = Modifier
                        .padding(horizontal = 6.dp),
                    fontWeight = FontWeight.SemiBold
                )

                if (item.author_details.rating != null){
                    Icon(
                        painter = painterResource(R.drawable.star_fill),
                        contentDescription = "" ,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 2.dp),
                        tint = MaterialTheme.colorScheme.imdbYellow
                    )

                    Text(
                        text = DigitHelper.digitByLang(item.author_details.rating.toString()),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.darkText,
                    )

                }




            }

            Text(
                text = item.content,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.darkText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Thin
            )


        }

    }

}