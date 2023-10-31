package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.Cyan
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.utils.MySpacerWidth
import com.farzin.imdb.utils.Utils

@Composable
fun MediaDetailTitleSection(
    name:String,
    isMovie:Boolean,
    date:String,
    status:String,
    runTime:List<Int>,
    numberOfEpisode : Int = 0
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp),
        horizontalAlignment = Alignment.Start,
    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
        ) {

            Text(
                text = name,
                maxLines = 2,
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .weight(0.08f),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Normal
            )

        }


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Text(
                text = status,
                modifier = Modifier
                    .wrapContentWidth(),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )

            MySpacerWidth(width = 8.dp)


            Text(
                text = Utils.extractYearFromDate(date),
                modifier = Modifier
                    .wrapContentWidth(),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )

            MySpacerWidth(width = 8.dp)


            runTime.forEachIndexed {index, time ->
            Text(
                    text = "${time}m",
                    modifier = Modifier
                        .wrapContentWidth(),
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )


                if (index != runTime.lastIndex){
                    Text(
                        text = " - ",
                        modifier = Modifier
                            .wrapContentWidth(),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )
                }

            }


        }

        if (!isMovie){

            MySpacerHeight(height = 12.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(R.string.episode_guide),
                    modifier = Modifier
                        .wrapContentWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.Cyan,
                    fontWeight = FontWeight.SemiBold
                )

                MySpacerWidth(width = 8.dp)

                Text(
                    text = "$numberOfEpisode ${stringResource(R.string.episodes)}",
                    modifier = Modifier
                        .wrapContentWidth(),
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.Thin
                )

            }

        }




    }


}