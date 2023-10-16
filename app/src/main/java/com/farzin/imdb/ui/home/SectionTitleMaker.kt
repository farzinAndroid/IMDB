package com.farzin.imdb.ui.home

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farzin.imdb.ui.theme.normalText

@Composable
fun SectionTitleMaker(title: String) {


        Text(
            text = title,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.normalText,
            modifier = Modifier
                .padding(start = 20.dp)
        )



}