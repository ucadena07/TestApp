package com.example.testapp.screens.systemPermissionsScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SystemPermissionScreenVm @Inject constructor() : ViewModel(){
    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    fun dismissDialog(){
        visiblePermissionDialogQueue.removeLast()
    }

    fun onPermissionResult(permission: String, isGranted: Boolean){
        if(!isGranted){
            visiblePermissionDialogQueue.add(0,permission)
        }
    }
}