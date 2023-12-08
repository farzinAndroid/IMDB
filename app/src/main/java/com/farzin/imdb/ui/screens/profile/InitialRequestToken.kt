package com.farzin.imdb.ui.screens.profile

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.utils.Constants
import com.farzin.imdb.viewmodel.DataStoreViewModel
import com.farzin.imdb.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun InitialRequestToken(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {

    var loading by remember { mutableStateOf(false) }

    if (dataStoreViewModel.getSessionId().isNullOrEmpty() || dataStoreViewModel.getAccountId().isNullOrEmpty()){
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



}