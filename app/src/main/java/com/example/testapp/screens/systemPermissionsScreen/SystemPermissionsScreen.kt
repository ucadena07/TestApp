package com.example.testapp.screens.systemPermissionsScreen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.testapp.layout.MainLayout

@Composable
fun SystemPermissionsScreen(navController: NavHostController, vm: SystemPermissionScreenVm) {
    MainLayout(navController = navController) {

        val dialogQueue = vm.visiblePermissionDialogQueue
        val cameraPermissionResultLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = {isGranted ->
                vm.onPermissionResult(
                    permission = Manifest.permission.CAMERA,
                    isGranted = isGranted
                )
            }
        )
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(onClick = { cameraPermissionResultLauncher.launch(Manifest.permission.CAMERA) }) {
                Text(text = "Request one permission")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Request multiple permission")
            }
        }
    }
}
