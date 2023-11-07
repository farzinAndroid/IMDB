package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerWidth

@Composable
fun MediaNoCommentCard() {

    MySpacerWidth(width = 8.dp)

    Card(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = Shapes().small,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.sectionContainerBackground)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(R.string.no_comment) ,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.darkText,
            )

        }

    }

}