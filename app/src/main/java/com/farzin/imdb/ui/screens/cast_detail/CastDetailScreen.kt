package com.farzin.imdb.ui.screens.cast_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.ui.screens.tvdetails.MediaDetailTopBarSection
import com.farzin.imdb.ui.screens.tvdetails.MediaOverViewSection
import com.farzin.imdb.viewmodel.CastDetailViewModel

@Composable
fun CastDetailScreen(
    id: Int,
    navController: NavController,
    castDetailViewModel: CastDetailViewModel = hiltViewModel(),
) {

    LaunchedEffect(id) {
        castDetailViewModel.getCastDetail(id)
    }


    var loading by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var poster by remember { mutableStateOf("") }
    var biography by remember { mutableStateOf("") }
    var bornDate by remember { mutableStateOf("") }
    var deathDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf(0) }
    var placeOfBirth by remember { mutableStateOf("") }
    var knownForDepartment by remember { mutableStateOf("") }
    var alternateNames by remember { mutableStateOf<List<String>>(emptyList()) }


    val result by castDetailViewModel.castDetail.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            name = result.data?.name ?: ""
            biography = result.data?.biography ?: ""
            poster = result.data?.profile_path ?: ""
            bornDate = result.data?.birthday ?: ""
            deathDate = result.data?.deathday ?: ""
            gender = result.data?.gender ?: 0
            placeOfBirth = result.data?.place_of_birth ?: ""
            knownForDepartment = result.data?.known_for_department ?: ""
            alternateNames = result.data?.also_known_as ?: emptyList()
        }

        is NetworkResult.Error -> {
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = false
        }
    }


    LazyColumn(modifier = Modifier.fillMaxSize()) {

        item {
            MediaDetailTopBarSection(name = name) {
                navController.popBackStack()
            }
        }

        item {
            MediaOverViewSection(
                genres = emptyList(),
                posterPath = poster,
                overView = biography,
                bornDate = bornDate,
                deathDate = deathDate
            )
        }

        item { KnownForSectionTV(id = id, navController = navController) }
        item { KnownForSectionMovie(id = id, navController = navController) }
        item { CastDetailSection(gender, placeOfBirth, knownForDepartment, alternateNames) }


    }


}