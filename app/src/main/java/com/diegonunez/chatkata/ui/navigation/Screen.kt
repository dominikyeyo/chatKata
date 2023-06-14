package com.diegonunez.chatkata.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Profile : Screen("profile_screen")
}