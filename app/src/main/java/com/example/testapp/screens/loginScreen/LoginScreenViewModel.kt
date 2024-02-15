package com.example.testapp.screens.loginScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.testapp.base.BaseViewModel
import com.example.testapp.model.dto.AuthRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel(){
    val authRequest = mutableStateOf(AuthRequest())

    init {

    }

    fun login(home: () -> Unit = {}){
        Log.d("","${authRequest.value.email},${authRequest.value.password}")
    }

}