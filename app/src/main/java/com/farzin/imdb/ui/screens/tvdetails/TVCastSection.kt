package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.tvDetail.Cast
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.screens.home.SectionStickyHeader
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.ui.theme.strongGray
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.TVDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TVCastSection(
    tvDetailViewModel: TVDetailViewModel = hiltViewModel(),
    mediaId: Int,
    navController: NavController,
) {

    LaunchedEffect(true) {
        tvDetailViewModel.getTVCastAndCrew(mediaId)
    }

    var loading by remember { mutableStateOf(false) }
    var director by remember { mutableStateOf("") }
    var writer by remember { mutableStateOf("") }


    var isSavedInFav by remember { mutableStateOf(false) }


    LaunchedEffect(true) {
        tvDetailViewModel.directorsList.collectLatest { directors ->

            director = when (directors) {
                is NetworkResult.Success -> {
                    val directorList = (directors.data ?: emptyList())
                    if (directorList.isEmpty()) {
                        ""
                    } else {
                        val firstFourDirectors = directorList.take(4).joinToString { it.name }
                        val remainingDirectorsCount = directorList.size - 4
                        val remainingDirectors =
                            if (remainingDirectorsCount > 0) " and $remainingDirectorsCount others" else ""
                        "$firstFourDirectors$remainingDirectors"
                    }
                }

                else -> ""
            }
        }
    }

    LaunchedEffect(true) {
        tvDetailViewModel.writersList.collectLatest { writers ->

            writer = when (writers) {
                is NetworkResult.Success -> {
                    val directorList = (writers.data ?: emptyList())
                    if (directorList.isEmpty()) {
                        ""
                    } else {
                        val firstFourDirectors = directorList.take(4).joinToString { it.name }
                        val remainingDirectorsCount = directorList.size - 4
                        val remainingDirectors =
                            if (remainingDirectorsCount > 0) " and $remainingDirectorsCount others" else ""
                        "$firstFourDirectors$remainingDirectors"
                    }
                }

                else -> ""
            }
        }
    }


    var castList by remember { mutableStateOf<List<Cast>>(emptyList()) }
    var name by remember { mutableStateOf("") }
    var profilePath by remember { mutableStateOf("") }
    var character by remember { mutableStateOf("") }

    val result by tvDetailViewModel.castAndCrew.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            castList = result.data?.cast?.take(10) ?: emptyList()
        }

        is NetworkResult.Error -> {
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.sectionContainerBackground),
            shape = MaterialTheme.shapes.extraSmall
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {

                SectionStickyHeader(stringResource(R.string.cast),
                    isHaveAnotherText = true,
                    headerSubtitle = stringResource(R.string.see_all),
                    headerOnClick = {navController.navigate(Screens.AllCastTV.route + "?id=${mediaId}")}
                    )

                MySpacerHeight(height = 8.dp)

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(castList) { cast ->
                        name = cast.name
                        character = cast.roles.filter { it.character.isNotBlank() }
                            .joinToString { it.character }
                        profilePath = cast.profile_path.toString()
                        CastCardItem(
                            profilePath = profilePath,
                            character = character,
                            name = name,
                            onCardClicked = {
                                navController.navigate(Screens.PersonDetail.route + "?id=${cast.id}")
                            },
                            id = cast.id,
                            job = cast.known_for_department
                        )
                    }
                }


                if (director != "") {
                    MySpacerHeight(height = 12.dp)

                    Text(
                        text = stringResource(R.string.directors),
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.darkText,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold
                    )



                    Text(
                        text = director,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.strongGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(vertical = 4.dp),
                        textAlign = TextAlign.Start,
                        maxLines = 2,
                    )
                }

                if (writer != "") {

                    MySpacerHeight(height = 12.dp)

                    Text(
                        text = stringResource(R.string.writers),
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.darkText,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold
                    )



                    Text(
                        text = writer,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.strongGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(vertical = 4.dp),
                        textAlign = TextAlign.Start,
                        maxLines = 2,
                    )
                }
            }
        }
    }
}