package com.farzin.imdb.navigation.setupNavgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.home.HomeScreen
import com.farzin.imdb.ui.search.SearchScreen
import com.farzin.imdb.ui.profile.ProfileScreen
import com.farzin.imdb.ui.service.ServiceScreen


@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    startDestination : String = Screens.Home.route
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){

        composable(Screens.Home.route){
            HomeScreen(navController = navController)
        }

        composable(Screens.Search.route){
            SearchScreen()
        }


        composable(Screens.Profile.route){
            ProfileScreen(navController)
        }


        composable(Screens.Service.route){
            ServiceScreen(navController)
        }

    }

}