package com.example.testapp.screens.systemPermissionsScreen

import android.Manifest
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.navigation.NavHostController
import com.example.testapp.components.shared.AudioPermissionTextProvider
import com.example.testapp.components.shared.CameraPermissionTextProvider
import com.example.testapp.components.shared.PermissionDialog
import com.example.testapp.components.shared.PhonePermissionTextProvider
import com.example.testapp.layout.MainLayout

@Composable
fun SystemPermissionsScreen(navController: NavHostController, vm: SystemPermissionScreenVm) {
    val context = LocalContext.current
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
    context.chec
        dialogQueue
            .reversed()
            .forEach{
            permission ->
                PermissionDialog(
                    permissionTextProvider = when(permission){
                                              Manifest.permission.CAMERA -> CameraPermissionTextProvider()
                                              Manifest.permission.RECORD_AUDIO -> AudioPermissionTextProvider()
                                              Manifest.permission.CALL_PHONE -> PhonePermissionTextProvider()
                        else -> return@forEach
                                                             },
                    isPermanentlyDeclined = false,
                    onDismiss = { /*TODO*/ },
                    onOkClick = { /*TODO*/ },
                    onGoToAppSettingsClick = { /*TODO*/ })
        }
    }
}
