package com.farzin.imdb.navigation

sealed class Screens(val route:String){

    object Home: Screens(route = "home_screen")
    object Search: Screens(route = "search_screen")
    object Profile: Screens(route = "profile_screen")
    object Service: Screens(route = "service_screen")
    object Settings: Screens(route = "settings_screen")
    object TVDetails: Screens(route = "tv_details_screen")
    object MovieDetails: Screens(route = "movie_details_screen")
    object TVComment: Screens(route = "tv_comment_screen")
    object MovieComment: Screens(route = "movie_comment_screen")
    object EpisodeGuide: Screens(route = "episode_guide_screen")
    object AllCastMovie: Screens(route = "all_cast_movie_screen")
    object AllCastTV: Screens(route = "all_cast_tv_screen")
    object PersonDetail: Screens(route = "person_detail_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }

}
