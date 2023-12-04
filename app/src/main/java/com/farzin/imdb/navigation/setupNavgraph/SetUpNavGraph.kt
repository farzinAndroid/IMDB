package com.farzin.imdb.navigation.setupNavgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.screens.all_cast.AllMovieCast
import com.farzin.imdb.ui.screens.all_cast.AllTVCast
import com.farzin.imdb.ui.screens.cast_detail.CastDetailScreen
import com.farzin.imdb.ui.screens.episode_guide.EpisodeGuideScreen
import com.farzin.imdb.ui.screens.home.HomeScreen
import com.farzin.imdb.ui.screens.images_screen.ImagesListScreen
import com.farzin.imdb.ui.screens.moviecomment.MovieCommentScreen
import com.farzin.imdb.ui.screens.moviedetails.MovieDetailsScreen
import com.farzin.imdb.ui.screens.play_video.VideoPlayerScreen
import com.farzin.imdb.ui.screens.profile.ProfileScreen
import com.farzin.imdb.ui.screens.search.SearchScreen
import com.farzin.imdb.ui.screens.service.ServiceScreen
import com.farzin.imdb.ui.screens.settings.SettingsScreen
import com.farzin.imdb.ui.screens.tvcomment.TVCommentScreen
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
            SearchScreen(navController)
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
            route = Screens.TVDetails.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id ->
                TVDetailsScreen(
                    tvId = id,
                    navController = navController
                )
            }


        }

        composable(
            route = Screens.MovieDetails.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id ->
                MovieDetailsScreen(
                    movieId = id,
                    navController = navController
                )
            }


        }


        composable(
            route = Screens.TVComment.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id ->
                TVCommentScreen(
                    mediaId = id,
                    navController = navController
                )
            }


        }


        composable(
            route = Screens.MovieComment.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id ->
                MovieCommentScreen(
                    mediaId = id,
                    navController = navController
                )
            }


        }

        composable(
            route = Screens.EpisodeGuide.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id ->
                EpisodeGuideScreen(
                    mediaId = id,
                    navController = navController
                )
            }


        }


        composable(
            route = Screens.AllCastMovie.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id ->
                AllMovieCast(
                    id = id,
                    navController = navController,
                )
            }
        }


        composable(
            route = Screens.AllCastTV.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id ->
                AllTVCast(
                    id = id,
                    navController = navController,
                )
            }
        }

        composable(
            route = Screens.PersonDetail.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id ->
                CastDetailScreen(
                    id = id,
                    navController = navController,
                )
            }
        }

        composable(
            route = Screens.Video.route + "?key={key}",
            arguments = listOf(
                navArgument("key") {
                    nullable = false
                    defaultValue = " "
                    type = NavType.StringType
                }
            )
        ) {

            it.arguments!!.getString("key")?.let { key ->
                VideoPlayerScreen(
                    key = key,
                    navController = navController,
                )
            }
        }


        composable(
            route = Screens.ImageList.route + "?id={id}?mediaType={mediaType}",
            arguments = listOf(
                navArgument("id") {
                    nullable = false
                    defaultValue = 0
                    type = NavType.IntType
                },
                navArgument("mediaType") {
                    nullable = false
                    defaultValue = " "
                    type = NavType.StringType
                }
            )
        ) {

            it.arguments!!.getInt("id").let { id ->
                it.arguments!!.getString("mediaType")?.let { mediaType ->
                    ImagesListScreen(
                        mediaId = id,
                        navController = navController,
                        mediaType = mediaType
                    )
                }
            }
        }


    }

}