package com.farzin.imdb.ui.service

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R

@Composable
fun ServiceDescription() {


    Text(
        text = stringResource(R.string.service_desc),
        color = Color.Gray,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Thin
    )

}