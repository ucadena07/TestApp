package com.example.testapp.screens.splashScreen


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.testapp.R
import com.example.testapp.authentication.AuthState
import com.example.testapp.navigation.ApplicationScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true){
        scale.animateTo(0.9f, tween(durationMillis = 800, easing = {OvershootInterpolator(8f).getInterpolation(it)}))
        delay(1000L)
        //check for auth state
        if(AuthState.getAuthDetails() != null){
            navController.navigate(ApplicationScreens.HomeScreen.name)
        }else{
            navController.navigate(ApplicationScreens.LoginScreen.name)
        }

    }
    Surface(
        modifier = Modifier
            .padding(15.dp)

            .size(330.dp)
            .scale(scale.value),
        color = Color(0xFF303a46),
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)

        ) {
        Column(
            modifier = Modifier
                .padding(15.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.netk_logo_dark), contentDescription = "logo")
        }

    }



}