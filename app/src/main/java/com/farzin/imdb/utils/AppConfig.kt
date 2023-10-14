package com.farzin.imdb.utils

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.imdb.viewmodel.DataStoreViewModel
import com.farzin.imdb.viewmodel.ProfileViewModel


@Composable
fun AppConfig(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStore: DataStoreViewModel = hiltViewModel(),
) {

    getDataStoreVariables(dataStore)

}

private fun getDataStoreVariables(dataStore: DataStoreViewModel) {


    Constants.SESSION_ID = dataStore.getSessionId().toString()
}