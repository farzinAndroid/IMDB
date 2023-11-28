package com.farzin.imdb.ui.screens.cast_detail

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.screens.home.SectionStickyHeader
import com.farzin.imdb.ui.screens.tvdetails.MediaDetailGenreItem
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.ui.theme.strongGray
import com.farzin.imdb.utils.MyDividerHorizontal
import com.farzin.imdb.utils.MySpacerHeight

@Composable
fun CastDetailSection(
    gender: Int = 0,
    placeOfBirth: String = "",
    knownForDepartment: String = "",
    alternateNames: List<String> = emptyList(),
) {

    val genderText = when (gender) {
        1 -> {
            stringResource(R.string.femaile)
        }

        2 -> {
            stringResource(R.string.male)
        }

        else -> {
            stringResource(R.string.not_specified)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        MySpacerHeight(height = 24.dp)

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


                SectionStickyHeader(stringResource(R.string.details))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp)
                            .padding(bottom = 12.dp),
                        horizontalAlignment = Alignment.Start

                    ) {
                        Text(
                            text = stringResource(R.string.gender),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.darkText
                        )

                        Text(
                            text = genderText,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Thin,
                            color = MaterialTheme.colorScheme.strongGray
                        )
                    }

                    MyDividerHorizontal()



                if (knownForDepartment.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .padding(start = 12.dp)
                            .padding(bottom = 12.dp),
                        horizontalAlignment = Alignment.Start

                    ) {
                        Text(
                            text = stringResource(R.string.known_for),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.darkText
                        )

                        Text(
                            text = knownForDepartment,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Thin,
                            color = MaterialTheme.colorScheme.strongGray
                        )
                    }

                    MyDividerHorizontal()
                }



                if (placeOfBirth.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .padding(start = 12.dp)
                            .padding(bottom = 12.dp),
                        horizontalAlignment = Alignment.Start

                    ) {
                        Text(
                            text = stringResource(R.string.place_of_birth),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.darkText
                        )

                        Text(
                            text = placeOfBirth,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Thin,
                            color = MaterialTheme.colorScheme.strongGray
                        )
                    }
                    MyDividerHorizontal()
                }


                if (alternateNames.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .padding(start = 12.dp)
                            .padding(bottom = 12.dp),
                        horizontalAlignment = Alignment.Start

                    ) {
                        Text(
                            text = stringResource(R.string.alternate_names),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.darkText
                        )

                        LazyRow(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth()
                        ) {
                            items(alternateNames) {
                                MediaDetailGenreItem(it)
                            }
                        }

                    }


                    MySpacerHeight(height = 8.dp)
                }


            }

        }

    }

}