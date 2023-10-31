package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.ui.theme.Cyan
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.font_standard
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.starColor
import com.farzin.imdb.utils.MyDividerHorizontal
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.MediaDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MediaRatingSection(
    rating: String,
    voteCount: Int,
    tvId: Int,
    mediaDetailViewModel: MediaDetailViewModel = hiltViewModel(),
) {

    LaunchedEffect(true) {
        mediaDetailViewModel.getRatedTV()
    }

    var loading by remember { mutableStateOf(false) }
    var isRated by remember { mutableStateOf(false) }
    var userRating by remember { mutableStateOf(0) }


    val result by mediaDetailViewModel.ratedTV.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            isRated = result.data?.results?.any {
                tvId == it.id
            } ?: false
            result.data?.results?.forEach {
                userRating = if (tvId == it.id) it.rating else 0
            }

        }

        is NetworkResult.Error -> {
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }


    }




    MySpacerHeight(height = 16.dp)

    MyDividerHorizontal()

    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(R.drawable.star_fill),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp),
                tint = MaterialTheme.colorScheme.imdbYellow
            )


            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = font_standard,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.darkText
                        )
                    ) {
                        append(rating)
                    }

                    withStyle(
                        style = SpanStyle(
                            fontFamily = font_standard,
                            fontWeight = FontWeight.Thin,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.darkText
                        )
                    ) {
                        append("/10")
                    }
                },
                modifier = Modifier
                    .padding(top = 4.dp)
            )


            Text(
                text = voteCount.toString(),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Gray,
                fontWeight = FontWeight.Thin,
            )

        }

        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (!isRated) {
                Icon(
                    painter = painterResource(R.drawable.star_unfill),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.Cyan
                )

                Text(
                    text = stringResource(R.string.rate_this),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.Cyan,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.star_fill),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.starColor
                )


                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = font_standard,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.darkText
                            )
                        ) {
                            append("$userRating")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontFamily = font_standard,
                                fontWeight = FontWeight.Thin,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.darkText
                            )
                        ) {
                            append("/10")
                        }
                    },
                    modifier = Modifier
                        .padding(top = 4.dp)
                )

                Text(
                    text = stringResource(R.string.your_rating),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.starColor,
                    fontWeight = FontWeight.Thin,
                )
            }


        }

    }


}