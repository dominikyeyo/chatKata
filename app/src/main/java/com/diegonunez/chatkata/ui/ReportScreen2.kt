package com.diegonunez.chatkata.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReportScreen2() {

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.LightGray)
    ) {
        Text(text = "Contacts", fontSize = 20.sp,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp))
        ContactList()
    }

}


@Composable
fun ContactList() {
    val contacts = listOf(
        Contact("John Doe", "+57 1234567890"),
        Contact("Jane Smith", "+57 9876543210"),
        Contact("Alice Johnson", "+57 5555555555"),
        Contact("Michael Brown", "+57 1111111111"),
        Contact("Emily Davis", "+57 2222222222"),
        Contact("Daniel Wilson", "+57 3333333333"),
        Contact("Olivia Taylor", "+57 4444444444"),
        Contact("William Miller", "+57 5555555555"),
        Contact("Sophia Anderson", "+57 6666666666"),
        Contact("David Thomas", "+57 7777777777"),
        // Agrega más contactos aquí
    )

    LazyColumn {
        items(contacts) { contact ->
            ContactItem(contact)
            Divider() // Agrega una línea divisora entre los contactos
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Muestra el nombre y el número de celular del contacto
        Text(
            text = contact.name,
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = contact.phoneNumber,
            style = MaterialTheme.typography.body2,
            color = Color.Gray
        )
    }
}

data class Contact(val name: String, val phoneNumber: String)