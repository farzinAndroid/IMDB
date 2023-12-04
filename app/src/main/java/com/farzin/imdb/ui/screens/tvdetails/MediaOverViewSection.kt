package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.R
import com.farzin.imdb.models.home.HomeGenre
import com.farzin.imdb.models.tvDetail.Genre
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.font_standard
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.utils.Util

@Composable
fun MediaOverViewSection(
    genres: List<Genre>,
    posterPath: String,
    overView: String,
    bornDate: String = "",
    deathDate:String = "",
    mediaType:String = ""
) {

    val tvGenres = Util(LocalContext.current).tvGenres
    val movieGenres = Util(LocalContext.current).movieGenres

    val filteredGenres = if (mediaType == "movie")
        filterGenres(genres,movieGenres)
    else
        filterGenres(genres,tvGenres)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 6.dp, horizontal = 8.dp)
    ) {

        Image(
            painter = rememberAsyncImagePainter(ImageHelper.appendImage(posterPath)),
            contentDescription = "",
            modifier = Modifier
                .weight(0.35f)
                .height(170.dp)
                .padding(horizontal = 4.dp),
            contentScale = ContentScale.FillBounds
        )


        Column(
            modifier = Modifier
                .weight(0.65f)
                .padding(start = 4.dp),
            horizontalAlignment = Alignment.Start,
        ) {

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(filteredGenres, key = { it.id }) {
                    MediaDetailGenreItem(it.name)
                }
            }

            Text(
                text = overView,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.darkText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(top = 8.dp),
                lineHeight = 18.sp,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )


            if (bornDate.isNotBlank() || bornDate.isNotEmpty()) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = font_standard,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.darkText
                            )
                        ) {
                            append(stringResource(R.string.born_in))
                        }


                        withStyle(
                            style = SpanStyle(
                                fontFamily = font_standard,
                                fontWeight = FontWeight.Thin,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.darkText
                            )
                        ) {
                            append(" $bornDate")
                        }
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.darkText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Start
                )
            }



            if (deathDate.isNotBlank() || deathDate.isNotEmpty()) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = font_standard,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.darkText
                            )
                        ) {
                            append(stringResource(R.string.died_in))
                        }


                        withStyle(
                            style = SpanStyle(
                                fontFamily = font_standard,
                                fontWeight = FontWeight.Thin,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.darkText
                            )
                        ) {
                            append(" $deathDate")
                        }
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.darkText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    textAlign = TextAlign.Start
                )
            }




        }


    }

}

private fun filterGenres(
    genresFromServer: List<Genre>,
    genresFromUtil: List<HomeGenre>
): List<HomeGenre> {
    val filteredGenres = mutableListOf<HomeGenre>()

    for (genreFromServer in genresFromServer) {
        val matchingGenreFromUtil = genresFromUtil.find { it.id == genreFromServer.id }
        if (matchingGenreFromUtil != null) {
            filteredGenres.add(matchingGenreFromUtil)
        }
    }

    return filteredGenres
}