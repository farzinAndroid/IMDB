package com.farzin.imdb.navigation

sealed class Screens(val route:String){

    object Home: Screens(route = "home_screen")
    object Search: Screens(route = "search_screen")
    object Profile: Screens(route = "profile_screen")

}