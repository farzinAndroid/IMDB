package com.farzin.imdb.ui.screens.search

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.SearchViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchTVSection(
    searchValue: String,
    onSearchValueChanged: (String) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val searchTVList = searchViewModel.searchedTV.collectAsLazyPagingItems()

    Column {

        SearchTextField(
            value = searchValue,
            onValueChanged = { onSearchValueChanged(it) },
            onSearchClicked = {
                if (searchValue.isEmpty() || searchValue.isBlank()) {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                } else {
                    scope.launch {
                        searchViewModel.searchTV(searchValue)
                        searchTVList.refresh()
                    }
                }
            }
        )


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            item { MySpacerHeight(height = 10.dp) }

            items(
                searchTVList.itemCount,
                searchTVList.itemKey { it.id },
                searchTVList.itemContentType { "searchTVList" }
            ) {
                val list = searchTVList[it]

                SearchMediaItem(
                    title = list?.name ?: "",
                    poster = list?.poster_path ?: "",
                    startYear = list?.first_air_date ?: "",
                    isMovie = false,
                    onClick = {
                        navController.navigate(Screens.TVDetails.route+"?id=${list?.id}")
                    }
                )
            }
        }
    }



}