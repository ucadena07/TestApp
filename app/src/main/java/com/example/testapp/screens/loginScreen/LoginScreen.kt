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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.testapp.components.shared.UnderlineTextComponent
import com.example.testapp.navigation.ApplicationScreens

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
          NormalTextComponent(value = "Login")
          HeadingTextComponent(value = "Welcome Back")
          Spacer(modifier = Modifier.heightIn(15.dp))
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
              Image(painter = painterResource(id = R.drawable.netk_logo_color), contentDescription = "logo")
          }

          Spacer(modifier = Modifier.heightIn(20.dp))
          NkTextField(label = "Email", icon = Icons.Default.Email)
          NkPasswordTextField(label = "Password", )
          Spacer(modifier = Modifier.heightIn(20.dp))
          UnderlineTextComponent(value = "Forgot Password?"){
              navController?.navigate(ApplicationScreens.ForgotScreen.name)
          }
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

