package com.example.testapp.screens.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
      NormalTextComponent(value = "Welcome")
  }

}
@Preview
@Composable
fun DefaultPreviewForLoginScreen(){
    LoginScreen(navController = null)
}