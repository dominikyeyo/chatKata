package com.diegonunez.chatkata.ui

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diegonunez.chatkata.MainActivity
import com.diegonunez.chatkata.ui.activity.MessagesActivity
import com.diegonunez.chatkata.ui.viewmodels.ChannelListViewModel
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun HomeScreen2(
    viewModel : ChannelListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var showDialog: Boolean by remember {
        mutableStateOf(false)
    }



    /*
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.LightGray)
    ) {
        Text(text = "Home Screen2", fontSize = 20.sp)
    }
    */


    if (showDialog) {
        CreateChannelDialog(
            dismiss = { channelName ->
                viewModel.createChannel(channelName)
                showDialog = false
            }
        )
    }

    ChannelsScreen(
        filters = Filters.`in`(
            fieldName = "type",
            values = listOf("gaming", "messaging", "commerce", "team", "livestream")
        ),
        title = "Chats",
        isShowingSearch = true,
        onItemClick = { channel ->
            context.startActivity(MessagesActivity.getIntent(context, channelId = channel.cid))
            //startActivity(MessagesActivity.getIntent(this, channelId = channel.cid))
        },
        onBackPressed = {
            //finish()
                        },
        onHeaderActionClick = {
            showDialog = true
        },
        onHeaderAvatarClick = {
            viewModel.logout()
            ///finish()
            //startActivity(Intent(this, MainActivity::class.java))
        }
    )

}

@Composable
private fun CreateChannelDialog(dismiss: (String) -> Unit) {

    var channelName by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = { dismiss(channelName) },
        title = {
            Text(text = "Enter Channel Name")
        },
        text = {
            TextField(
                value = channelName,
                onValueChange = {channelName = it}
            )
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { dismiss(channelName) }
                ) {
                    Text(text = "Create Channel")
                }
            }
        }
    )
}
