package com.diegonunez.chatkata.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Chats.route
    ) {
        composable(route = BottomBarScreen.Chats.route) {
            ChatScreen()
            //context.startActivity(Intent(context, ChannelListActivity::class.java))
        }
        composable(route = BottomBarScreen.Contacts.route) {
            ContactsScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen2()
        }
    }
}