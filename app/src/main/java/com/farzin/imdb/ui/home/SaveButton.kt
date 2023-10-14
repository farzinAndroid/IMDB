package com.farzin.imdb.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.addBackground
import com.farzin.imdb.ui.theme.saveButtonBackground

@Composable
fun SaveButton(onClick: () -> Unit) {


    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.saveButtonBackground)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ){

        Icon(
            painter = painterResource(R.drawable.add),
            contentDescription = "",
            modifier = Modifier
                .size(10.dp),
            tint = MaterialTheme.colorScheme.addBackground
        )

    }


}