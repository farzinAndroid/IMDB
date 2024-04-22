package com.farzin.imdb.ui.screens.search

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.MovieResult
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.SearchViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchMovieSection(
    searchValue: String,
    onSearchValueChanged: (String) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var loading by remember { mutableStateOf(false) }
    var searchMovieList by remember { mutableStateOf<List<MovieResult>>(emptyList()) }

    val result by searchViewModel.searchedMovies.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            searchMovieList = result.data?.results ?: emptyList()
        }

        is NetworkResult.Error -> {
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    Column {

        SearchTextField(
            value = searchValue,
            onValueChanged = { onSearchValueChanged(it) },
            onSearchClicked = {
                if (searchValue.isEmpty() || searchValue.isBlank()) {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                } else {
                    scope.launch {
                        searchViewModel.searchMovie(searchValue)
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

            items(searchMovieList) { item ->
                SearchMediaItem(
                    title = item.title,
                    poster = item.poster_path ?: "",
                    startYear = item.release_date,
                    isMovie = true,
                    onClick = {
                        navController.navigate(Screens.MovieDetails.route + "?id=${item.id}")
                    }
                )
            }
        }

    }


}