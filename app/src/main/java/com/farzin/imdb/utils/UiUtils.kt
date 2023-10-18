package com.farzin.imdb.utils

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun MySpacerWidth(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun MySpacerHeight(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun InitialRequestToken(
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        profileViewModel.getInitialRequestToken()

        profileViewModel.initialRequestToken.collectLatest { reqToken ->
            when (reqToken) {
                is NetworkResult.Success -> {
                    loading = false
                    reqToken.data?.request_token?.let {
                        Constants.REQUEST_TOKEN = it
                        Log.e("TAG","this id from composable $it")
                    }
                }

                is NetworkResult.Error -> {
                    loading = false
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }


    }


}

@Composable
fun MyDividerHorizontal() {

    Divider(
        color = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    )

}
@Composable
fun MyDividerVertical() {

    Divider(
        color = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    )

}
