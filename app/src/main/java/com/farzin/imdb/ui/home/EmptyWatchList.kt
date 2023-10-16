package com.farzin.imdb.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.normalText

@Composable
fun EmptyWatchList(onClick:()->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.add_service_preffered),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.normalText,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = stringResource(R.string.add_service_preffered_desc),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(top = 10.dp)
                .width(300.dp),
            textAlign = TextAlign.Center
        )


        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.imdbYellow,
            ),
            elevation = ButtonDefaults.buttonElevation(4.dp),
            modifier = Modifier
                .height(50.dp)
                .padding(top = 12.dp),
        ) {
            Text(
                text = stringResource(R.string.add_service),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Normal,
            )
        }

    }


}