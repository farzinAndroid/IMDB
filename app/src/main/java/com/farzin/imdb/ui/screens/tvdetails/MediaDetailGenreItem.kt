package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.utils.MySpacerWidth

@Composable
fun MediaDetailGenreItem(
    genre:String
) {

    Row {
        MySpacerWidth(width = 8.dp)


        Box(
            modifier = Modifier
                .height(40.dp)
                .wrapContentWidth()
                .clip(Shapes().small)
                .border(1.dp, MaterialTheme.colorScheme.darkText,Shapes().small)
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = genre,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.darkText
            )
        }


    }



}