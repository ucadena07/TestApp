package com.example.testapp.screens.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.testapp.R
import com.example.testapp.components.shared.HeadingTextComponent
import com.example.testapp.components.shared.NkButton
import com.example.testapp.components.shared.NkPasswordTextField
import com.example.testapp.components.shared.NkTextField
import com.example.testapp.components.shared.NormalTextComponent
import com.example.testapp.network.ApplicationScreens

@Composable
fun LoginScreen(navController: NavHostController?) {
  Surface(
      color = Color.White,
      modifier = Modifier
          .fillMaxSize()
          .background(Color.White)
          .padding(28.dp)
  ) {
      Column(modifier = Modifier.fillMaxSize()) {
          NormalTextComponent(value = "Welcome")
          HeadingTextComponent(value = "Portal Sign In")
          Spacer(modifier = Modifier.heightIn(20.dp))
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
              Text(
                  modifier = Modifier
                      .padding(15.dp),
                  text = "Netkinetix",
                  style = MaterialTheme.typography.headlineLarge,
                  color = Color.DarkGray
              )
          }

          Spacer(modifier = Modifier.heightIn(20.dp))
          NkTextField(label = "Email", icon = Icons.Default.Email)
          NkPasswordTextField(label = "Password", )
          Spacer(modifier = Modifier.heightIn(20.dp))
          NkButton(value = "Login")
      }

  }

}
@Preview
@Composable
fun DefaultPreviewForLoginScreen(){
    LoginScreen(navController = null)
}

