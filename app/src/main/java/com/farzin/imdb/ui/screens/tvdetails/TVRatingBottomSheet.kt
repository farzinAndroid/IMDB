package com.farzin.imdb.ui.screens.tvdetails

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.tvDetail.AddRating
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.starBlue
import com.farzin.imdb.utils.MySpacerWidth
import com.farzin.imdb.viewmodel.TVDetailViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TVRatingBottomSheet(
    name: String,
    tvId: Int,
    tvDetailViewModel: TVDetailViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var selectedStars by remember { mutableIntStateOf(0) }
    var message by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    val result by tvDetailViewModel.addRating.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            message = result.data?.status_message ?: ""
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
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
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "${stringResource(R.string.rate_desc)} $name",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .padding(horizontal = 8.dp),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Normal,
            maxLines = 2,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.darkText
        )

        // stars
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(10) { index ->
                val starIcon =
                    if (index < selectedStars) painterResource(R.drawable.star_fill) else painterResource(
                        R.drawable.star_unfill
                    )

                IconButton(
                    onClick = {
                        selectedStars = index + 1
                    },
                    modifier = Modifier
                        .size(26.dp)
                ) {
                    Icon(
                        painter = starIcon,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.starBlue
                    )
                }
                MySpacerWidth(width = 8.dp)

            }
        }


        Button(
            onClick = {
                scope.launch {
                    tvDetailViewModel.addRating(tvId, AddRating(selectedStars))
                }

                scope.launch {
                    delay(2000)
                    tvDetailViewModel.getRatedTV()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp),
            shape = Shapes().extraSmall,
            enabled = selectedStars != 0
        ) {
            if(loading){
                Text(
                    text = stringResource(R.string.please_wait),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }else{
                Text(
                    text = stringResource(R.string.rate),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }

        }


    }

}