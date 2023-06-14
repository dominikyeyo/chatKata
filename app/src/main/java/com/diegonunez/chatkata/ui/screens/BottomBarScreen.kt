package com.diegonunez.chatkata.ui.screens

import com.diegonunez.chatkata.R


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    object Chats: BottomBarScreen(
        route = "chats",
        title = "Chats",
        icon = R.drawable.ic_bottom_chat,
        icon_focused = R.drawable.ic_bottom_chat_focused
    )

    // for report
    object Contacts: BottomBarScreen(
        route = "contacts",
        title = "New",
        icon = R.drawable.ic_bottom_new_chat,
        icon_focused = R.drawable.ic_bottom_new_chat_focused
    )

    // for report
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.ic_bottom_profile,
        icon_focused = R.drawable.ic_bottom_profile_focused
    )

}