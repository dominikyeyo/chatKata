package com.diegonunez.chatkata.ui

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diegonunez.chatkata.ui.HomeScreen2
import com.diegonunez.chatkata.ui.ProfileScreen2
import com.diegonunez.chatkata.ui.ReportScreen2
import com.diegonunez.chatkata.ui.activity.ChannelListActivity

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen2()
            //context.startActivity(Intent(context, ChannelListActivity::class.java))
        }
        composable(route = BottomBarScreen.Report.route) {
            ReportScreen2()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen2()
        }
    }
}