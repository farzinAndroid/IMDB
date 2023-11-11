package com.farzin.imdb.navigation.setupNavgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.screens.moviedetails.MovieDetailsScreen
import com.farzin.imdb.ui.screens.comment.CommentScreen
import com.farzin.imdb.ui.screens.home.HomeScreen
import com.farzin.imdb.ui.screens.profile.ProfileScreen
import com.farzin.imdb.ui.screens.search.SearchScreen
import com.farzin.imdb.ui.screens.service.ServiceScreen
import com.farzin.imdb.ui.screens.settings.SettingsScreen
import com.farzin.imdb.ui.screens.tvdetails.TVDetailsScreen


@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    startDestination: String = Screens.Home.route,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Screens.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screens.Search.route) {
            SearchScreen()
        }


        composable(Screens.Profile.route) {
            ProfileScreen(navController)
        }


        composable(Screens.Service.route) {
            ServiceScreen(navController)
        }

        composable(Screens.Settings.route) {
            SettingsScreen(navController)
        }

        composable(
            route = Screens.TVDetails.route+"?id={id}",
            arguments = listOf(
                navArgument("id"){
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id->
                    TVDetailsScreen(
                        tvId =id,
                        navController = navController
                    )
            }


        }

        composable(
            route = Screens.MovieDetails.route+"?id={id}",
            arguments = listOf(
                navArgument("id"){
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id->
                MovieDetailsScreen(
                    movieId =id,
                    navController = navController
                )
            }


        }


        composable(
            route = Screens.Comment.route+"?id={id}",
            arguments = listOf(
                navArgument("id"){
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id->
                CommentScreen(
                    mediaId =id,
                    navController = navController
                )
            }


        }


    }

}