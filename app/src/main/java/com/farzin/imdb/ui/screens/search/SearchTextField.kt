package com.farzin.imdb.ui.screens.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    value:String,
    onValueChanged : (String) -> Unit,
    onSearchClicked:()->Unit
) {



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = { onSearchClicked() },
            modifier = Modifier
                .weight(0.2f)
        ) {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp),
                tint = MaterialTheme.colorScheme.darkText
            )
        }


        TextField(
            value = value,
            onValueChange ={
                onValueChanged(it)
            },
            modifier = Modifier.weight(0.8f),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.imdbYellow,
                focusedIndicatorColor = MaterialTheme.colorScheme.imdbYellow, // Remove the line when focused
                unfocusedIndicatorColor = Color.Gray, // Remove the line when not focused
                containerColor = Color.Transparent, // Make the background transparent
                textColor = MaterialTheme.colorScheme.darkText
            ),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true
        )


    }



}