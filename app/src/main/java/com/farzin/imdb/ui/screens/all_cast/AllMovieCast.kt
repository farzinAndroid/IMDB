package com.farzin.imdb.ui.screens.all_cast

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.movieDetail.Cast
import com.farzin.imdb.ui.screens.tvdetails.MediaDetailTopBarSection
import com.farzin.imdb.viewmodel.MovieDetailViewModel

@Composable
fun AllMovieCast(
    id: Int,
    navController: NavController,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
) {


    var movieCast by remember { mutableStateOf<List<Cast>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var profilePath by remember { mutableStateOf("") }
    var character by remember { mutableStateOf("") }


    LaunchedEffect(id) {
        movieDetailViewModel.getMovieCastAndCrew(id)
    }

    val result by movieDetailViewModel.castAndCrew.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            movieCast = result.data?.cast ?: emptyList()
        }

        is NetworkResult.Error -> {
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            MediaDetailTopBarSection(
                name = stringResource(R.string.cast)
            ) {
                navController.popBackStack()
            }
        }

        items(movieCast) { cast ->
            name = cast.name
            character = cast.character
            profilePath = cast.profile_path.toString()

            CastItem(
                name = name,
                profilePath = profilePath,
                character = character,
                onCardClicked = {}
            )
        }
    }


}