package com.diegonunez.chatkata.ui.activity

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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
import com.diegonunez.chatkata.R
import com.diegonunez.chatkata.ui.theme.AnimatedSplashScreenDemoTheme
import com.diegonunez.chatkata.ui.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeToEvents()
        setContent {
            AnimatedSplashScreenDemoTheme {
                LoginScreen()
            }
        }
    }

    @Composable
    fun LoginScreen() {

        var username by remember {
            mutableStateOf(TextFieldValue(""))
        }

        var password by remember {
            mutableStateOf(TextFieldValue(""))
        }


        var showProgress: Boolean by remember {
            mutableStateOf(false)
        }

        viewModel.loadingState.observe(this, Observer { uiLoadingState ->
            showProgress = when (uiLoadingState) {
                is LoginViewModel.UiLoadingState.Loading -> {
                    true
                }

                is LoginViewModel.UiLoadingState.NotLoading -> {
                    false
                }
            }
        })

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 35.dp, end = 35.dp)
        ) {

            val (
                logo, usernameTextField,passWordTextField, btnLoginAsUser, progressBar
            ) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.chat_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .constrainAs(logo) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, margin = 100.dp)
                    }
            )

            OutlinedTextField(
                value = username,
                onValueChange = { newValue -> username = newValue },
                label = { Text(text = "Enter Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(usernameTextField) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(logo.bottom, margin = 32.dp)
                    },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(passWordTextField) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(usernameTextField.bottom, margin = 28.dp)
                    },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Button(
                onClick = {
                    viewModel.loginUser(username.text, getString(R.string.jwt_token))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(btnLoginAsUser) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(passWordTextField.bottom, margin = 28.dp)
                    }
            ) {
                Text(text = "LOGIN")
            }

            if (showProgress) {
                CircularProgressIndicator(
                    modifier = Modifier.constrainAs(progressBar) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(btnLoginAsUser.bottom, margin = 16.dp)
                    }
                )
            }
        }

    }

    private fun subscribeToEvents() {

        lifecycleScope.launchWhenStarted {

            viewModel.loginEvent.collect { event ->

                when(event) {
                    is LoginViewModel.LogInEvent.ErrorInputTooShort -> {
                        showToast("Invalid! Enter more than 3 characters.")
                    }

                    is LoginViewModel.LogInEvent.ErrorLogIn -> {
                        val errorMessage = event.error
                        showToast("Error: $errorMessage")
                    }

                    is LoginViewModel.LogInEvent.Success -> {
                        showToast("Login Successful!")
                        startActivity(Intent(this@LoginActivity, MainScreenActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}