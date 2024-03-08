package com.example.testapp.screens.loginScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.authentication.AuthState
import com.example.testapp.helpers.connectivity.jwt.getUserDetailsFromToken
import com.example.testapp.model.AuthRequest
import com.example.testapp.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(private  val repo: AccountRepository) : ViewModel(){
    val authRequest = mutableStateOf(AuthRequest())
    val errorMsg = mutableStateOf("")

    fun login(home: () -> Unit = {}){
        errorMsg.value = ""
        viewModelScope.launch {
            val resp = repo.login(authRequest.value)
            Log.d("API RESP",resp.toString())
            if(resp.isSuccess){
                AuthState.login(getUserDetailsFromToken(resp.result?.token!!))
                home()
            }else{
                errorMsg.value = "Something went wrong"
            }

        }

    }

}