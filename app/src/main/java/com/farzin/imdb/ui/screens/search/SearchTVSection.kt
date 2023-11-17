package com.farzin.imdb.ui.screens.search

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchTVSection(searchValue: String, onSearchValueChanged: (String) -> Unit) {


    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        stickyHeader {
            SearchTextField(
                value = searchValue,
                onValueChanged = { onSearchValueChanged(it) },
                onSearchClicked = {
                    Toast.makeText(context, searchValue, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}