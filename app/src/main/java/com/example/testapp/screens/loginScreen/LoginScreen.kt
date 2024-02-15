package com.example.testapp.screens.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.testapp.R
import com.example.testapp.components.shared.HeadingTextComponent
import com.example.testapp.components.shared.NkButton
import com.example.testapp.components.shared.NkPasswordTextField
import com.example.testapp.components.shared.NkTextField
import com.example.testapp.components.shared.NormalTextComponent
import com.example.testapp.components.shared.UnderlineTextComponent
import com.example.testapp.model.dto.AuthRequest
import com.example.testapp.navigation.ApplicationScreens
import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.FormResult
import me.naingaungluu.formconductor.composeui.field
import me.naingaungluu.formconductor.composeui.form

@Composable
fun LoginScreen(navController: NavHostController?, vm: LoginScreenViewModel?) {
  Surface(
      color = Color.White,
      modifier = Modifier
          .fillMaxSize()
          .background(Color.White)
          .padding(28.dp)
  ) {
      val scrollState = rememberScrollState()
      LaunchedEffect(Unit) { scrollState.animateScrollTo(100) }
      Column(modifier = Modifier
          .fillMaxSize()
          .verticalScroll(scrollState)) {
          form(AuthRequest::class) {
              NormalTextComponent(value = "Login")
              HeadingTextComponent(value = "Welcome Back")
              Spacer(modifier = Modifier.height(15.dp))
              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                  Image(painter = painterResource(id = R.drawable.netk_logo_color), contentDescription = "logo")
              }

              Spacer(modifier = Modifier.height(20.dp))
              field(AuthRequest::email) {
                  NkTextField(label = "Email",
                      icon = Icons.Default.Email,value = state.value?.value.orEmpty(),
                      onValueChange = {
                          setField(it)
                          vm!!.authRequest.value = vm.authRequest.value.copy(email = it)
                      },
                      isError = resultState.value is FieldResult.Error)
              }
              field(AuthRequest::password) {
                  NkPasswordTextField(label = "Password",
                      onValueChange ={
                          setField(it)
                          vm!!.authRequest.value = vm.authRequest.value.copy(password = it)

                      },
                      isError = resultState.value is FieldResult.Error )
              }

              Spacer(modifier = Modifier.height(40.dp))
              UnderlineTextComponent(value = "Forgot Password?"){
                  navController?.navigate(ApplicationScreens.ForgotScreen.name)
              }
              Spacer(modifier = Modifier.height(20.dp))

              NkButton(value = "Login", enabled = this.formState.value is FormResult.Success){
                  vm!!.login(){
                      navController!!.navigate(ApplicationScreens.HomeScreen.name)
                  }
              }

          }

      }

  }

}
@Preview
@Composable
fun DefaultPreviewForLoginScreen(){
    LoginScreen(navController = null,null)
}

