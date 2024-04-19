package com.example.testapp.screens.systemPermissionsScreen

import android.Manifest

import android.content.pm.PackageManager

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings

import androidx.navigation.NavHostController

import com.example.testapp.components.shared.AudioPermissionTextProvider
import com.example.testapp.components.shared.CameraPermissionTextProvider
import com.example.testapp.components.shared.PermissionDialog
import com.example.testapp.components.shared.PhonePermissionTextProvider
import com.example.testapp.layout.MainLayout
import com.example.testapp.utils.openAppSettings
@Composable
fun SystemPermissionsScreen(navController: NavHostController, vm: SystemPermissionScreenVm) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    MainLayout(navController = navController) {
        val permissionToRequest = arrayOf(
            Manifest.permission.CALL_PHONE,
            Manifest.permission.RECORD_AUDIO
        )

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
        val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {perms ->
                permissionToRequest.forEach{permission ->
                    vm.onPermissionResult(
                        permission = permission,
                        isGranted = perms[permission] == true
                    )
                }

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
            Button(onClick = { multiplePermissionResultLauncher.launch(permissionToRequest)}) {
                Text(text = "Request multiple permission")
            }
        }

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
                    isPermanentlyDeclined = context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED,
                    onDismiss = { vm.dismissDialog() },
                    onOkClick = {
                                vm.dismissDialog()
                                multiplePermissionResultLauncher.launch(
                                    arrayOf(permission)
                                )
                    },
                    onGoToAppSettingsClick = {activity.openAppSettings()}
                )
        }

    }

}

