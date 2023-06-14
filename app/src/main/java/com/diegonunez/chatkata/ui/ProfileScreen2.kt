package com.diegonunez.chatkata.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diegonunez.chatkata.R

@Composable
fun ProfileScreen2() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen de perfil
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(120.dp)
                .clip(CircleShape)
        )

        // Nombre del usuario
        Text(
            text = "Diego Nunez",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Descripción o información adicional
        Text(
            text = "¡Hola! Soy Diego Nunez. Me encanta programar y explorar nuevas tecnologías.",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Botón de seguimiento
        Button(
            onClick = { /* Acción al hacer clic */ },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text = "Editar")
        }
    }
}