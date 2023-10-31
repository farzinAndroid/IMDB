package com.farzin.imdb.navigation

sealed class Screens(val route:String){

    object Home: Screens(route = "home_screen")
    object Search: Screens(route = "search_screen")
    object Profile: Screens(route = "profile_screen")
    object Service: Screens(route = "service_screen")
    object Settings: Screens(route = "settings_screen")
    object TVDetails: Screens(route = "tv_details_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }

}
