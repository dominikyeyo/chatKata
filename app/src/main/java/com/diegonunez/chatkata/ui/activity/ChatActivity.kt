package com.diegonunez.chatkata.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ChatActivity : ComponentActivity() {

    private lateinit var contactId: String
    private lateinit var contactName: String
    private lateinit var contactPhoneNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Obtener los valores del Intent
        contactId = intent.getStringExtra("contactId") ?: ""
        contactName = intent.getStringExtra("contactName") ?: ""
        contactPhoneNumber = intent.getStringExtra("contactPhoneNumber") ?: ""

        setContent {
            ChatScreen(contactId, contactName, contactPhoneNumber)
        }
    }
}

@Composable
fun ChatScreen(
    contactId: String,
    contactName: String,
    contactPhoneNumber: String
) {
    val messageState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(text = contactName) },
            backgroundColor = Color.Blue,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Lista de mensajes
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = messageState.value,
                    onValueChange = { messageState.value = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text(text = "Escribe un mensaje...") }
                )

                Button(
                    onClick = { /* Lógica para enviar el mensaje */ },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(text = "Enviar")
                }
            }
        }
    }
}