package com.farzin.imdb.navigation.setupNavgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.home.HomeScreen
import com.farzin.imdb.ui.search.SearchScreen


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
            HomeScreen()
        }

        composable(Screens.Search.route){
            SearchScreen()
        }

    }

}