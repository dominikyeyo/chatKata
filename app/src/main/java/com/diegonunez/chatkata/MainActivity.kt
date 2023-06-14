package com.diegonunez.chatkata

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.diegonunez.chatkata.ui.activity.ChannelListActivity
import com.diegonunez.chatkata.ui.theme.AnimatedSplashScreenDemoTheme
import androidx.navigation.compose.rememberNavController
//import com.diegonunez.chatkata.ui.BottomNav
import com.diegonunez.chatkata.ui.navigation.SetupNavGraph
import com.diegonunez.chatkata.ui.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /*
        setContent {
            AnimatedSplashScreenDemoTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
        */

        setContent {
            AnimatedSplashScreenDemoTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }



        /*
        setContent {
            AnimatedSplashScreenDemoTheme {
                MainScreen()
            }
        }
        */



        /*
        subscribeToEvents()


        setContent {
            OwnChatAppTheme {
                LoginScreen()
            }
        }
        */
    }


    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}