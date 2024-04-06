package com.example.testapp

import android.Manifest
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.testapp.helpers.connectivity.NetworkConnectivityObserver
import com.example.testapp.navigation.ApplicationNavigation
import com.example.testapp.ui.theme.TestAppTheme
import com.example.testapp.utils.SD
import com.example.testapp.utils.hasRequiredPermissions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!hasRequiredPermissions(applicationContext)){
            ActivityCompat.requestPermissions(
                this, SD.CAMERAX_PERMISSIONS,0
            )
        }
        setContent {
            TestAppTheme {
                TestApp()
            }
        }
    }


}

@Composable
fun TestApp(){

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 5.dp

    ) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            ApplicationNavigation()
        }
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Preview
@Composable
fun TestAppPreview() {
    TestAppTheme {
        TestApp()
    }
}