package com.farzin.imdb.ui.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.viewmodel.DataStoreViewModel
import com.farzin.imdb.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {


   if (!dataStoreViewModel.getSessionId().isNullOrBlank() || !dataStoreViewModel.getAccountId().isNullOrBlank()){
       Profile(navController)
   }else{
       when(profileViewModel.screenState){
           ProfileState.NOTLOGGED->{
               Login(navController)
           }
           ProfileState.LOGGED->{
               Profile(navController)
           }

       }
   }


}


@Composable
fun Login(navController: NavController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {


        item { LoginTopBar() }


        item { LoginSection() }


    }

}