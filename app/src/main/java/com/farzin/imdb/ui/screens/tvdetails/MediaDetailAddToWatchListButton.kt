package com.farzin.imdb.ui.screens.tvdetails

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.darkText

@SuppressLint("SuspiciousIndentation")
@Composable
fun MediaDetailAddToWatchListButton(
    buttonBackGround: Color,
    buttonBorderColor: Color,
    onClick: () -> Unit,
    isInWatchList: Boolean,
) {

    var buttonText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        if (!isInWatchList) {

            buttonText = stringResource(R.string.add_to_watchList)

            Button(
                onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(buttonBackGround),
                modifier = Modifier
                    .fillMaxSize(),
                shape = Shapes().extraSmall,
                elevation = ButtonDefaults.buttonElevation(4.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Icon(
                        painter = painterResource(R.drawable.add),
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp),
                        tint = MaterialTheme.colorScheme.darkText
                    )

                    Text(
                        text = buttonText,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.darkText,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }


            }
        } else {

            buttonText = stringResource(R.string.added_to_watchList)

            OutlinedButton(
                onClick = { onClick() },
                border = BorderStroke(1.dp, buttonBorderColor),
                modifier = Modifier
                    .fillMaxSize(),
                shape = Shapes().extraSmall
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Icon(
                        painter = painterResource(R.drawable.check),
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp),
                        tint = MaterialTheme.colorScheme.darkText
                    )

                    Text(
                        text = buttonText,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.darkText,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
            }
        }
    }


}