package com.farzin.imdb.ui.screens.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.models.database.PersonDBModel
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.screens.home.EmptySection
import com.farzin.imdb.ui.screens.home.SectionStickyHeader
import com.farzin.imdb.ui.screens.tvdetails.CastCardItem
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.Constants
import com.farzin.imdb.utils.DigitHelper
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritePersonSection(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {


    var favPersonList by remember { mutableStateOf<List<PersonDBModel>>(emptyList()) }


    LaunchedEffect(true){
        profileViewModel.allPerson.collectLatest {result->
            favPersonList = result
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        MySpacerHeight(height = 20.dp)


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

                SectionStickyHeader(
                    headerTitle = "${stringResource(R.string.fav_celebs)} ${DigitHelper.digitByLang(favPersonList.size.toString())}",
                )

                MySpacerHeight(height = 8.dp)

                if (favPersonList.isEmpty() && Constants.SESSION_ID.isNotEmpty()) {

                    EmptySection(
                        onClick = {
                            navController.navigate(Screens.Search.route)
                        },
                        title = stringResource(R.string.your_celebs_empty),
                        subtitle = stringResource(R.string.add_your_fav_celeb),
                        isHaveButton = false
                    )

                } else {

                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(
                            favPersonList,
                            key = { it.id }
                        ) { item ->
                            CastCardItem(
                                name = item.name,
                                profilePath = item.image,
                                character = item.job,
                                onCardClicked = {navController.navigate(Screens.PersonDetail.route + "?id=${item.id}")},
                                id = item.id,
                                job = item.job,
                                modifier = Modifier.animateItemPlacement()
                            )
                        }
                    }

                }

                MySpacerHeight(height = 10.dp)

            }


        }

    }

}