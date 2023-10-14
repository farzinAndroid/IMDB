package com.farzin.imdb.ui.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.normalText
import java.sql.Ref

@Composable
fun CustomIMDBButton(
    onClick:()->Unit
) {

    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 30.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            focusedElevation = 4.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.imdbYellow,
        )
    ) {

        Text(
            text = stringResource(R.string.login),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.normalText
        )

    }

}