package com.example.testapp.screens.loginScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.testapp.base.BaseViewModel
import com.example.testapp.model.dto.AuthRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel(){

    var authRequest : AuthRequest = AuthRequest()

    init {

    }

    fun login(){
        Log.d("","${authRequest.email},${authRequest.password}")
    }

}