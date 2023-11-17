package com.farzin.imdb.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.sectionContainerBackground

@Composable
fun SearchScreen() {


    val tabTitles = listOf(
        stringResource(R.string.tv),
        stringResource(R.string.movie),
    )

    var selectedTabIndex by remember { mutableStateOf(0) }
    var searchTVValue by rememberSaveable { mutableStateOf("") }
    var searchMovieValue by rememberSaveable { mutableStateOf("") }



    Column {

        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.padding(horizontal = 16.dp),
            containerColor = MaterialTheme.colorScheme.sectionContainerBackground,
            indicator = { line ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(line[selectedTabIndex])
                        .height(3.dp)
                        .background(MaterialTheme.colorScheme.imdbYellow)

                )
            }
        ) {

            tabTitles.forEachIndexed { index, title ->

                Tab(
                    selected = (selectedTabIndex == index),
                    onClick = {
                        selectedTabIndex = index
                    },
                    selectedContentColor = MaterialTheme.colorScheme.imdbYellow,
                    unselectedContentColor = Color.Gray,
                    text = {
                        Row {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.darkText
                            )
                        }
                    }
                )

            }


        }

        when (selectedTabIndex) {
            0 -> {
                SearchTVSection(searchValue = searchTVValue, onSearchValueChanged = { newValue ->
                    searchTVValue = newValue
                })
            }

            1 -> {
                SearchMovieSection(searchValue = searchMovieValue, onSearchValueChanged = { newValue ->
                    searchMovieValue = newValue
                })
            }
        }

    }

}