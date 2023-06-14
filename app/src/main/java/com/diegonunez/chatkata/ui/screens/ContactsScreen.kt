package com.diegonunez.chatkata.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.diegonunez.chatkata.ui.viewmodels.MainScreenViewModel

@Composable
fun ContactsScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.loadContacts(context)
    }


    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.LightGray)
    ) {
        Text(
            text = "Contacts",
            fontSize = 20.sp,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ContactList(contacts = state.contacts){ contact ->
            viewModel.openChat(context,contact.id)
        }
    }
}


@Composable
fun ContactList(contacts: List<Contact>, onItemClick: (Contact) -> Unit) {

    LazyColumn {
        items(contacts) { contact ->
            ContactItem(contact, onItemClick)
            Divider()
        }
    }
}

@Composable
fun ContactItem(contact: Contact, onItemClick: (Contact) -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onItemClick(contact) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = contact.name,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
            Text(text = contact.phoneNumber)
        }
    }
}

data class Contact(val id: String, val name: String, val phoneNumber: String)