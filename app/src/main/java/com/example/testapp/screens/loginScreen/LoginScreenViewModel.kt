package com.example.testapp.screens.loginScreen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.authentication.AuthState
import com.example.testapp.helpers.connectivity.IConnectivityObserver
import com.example.testapp.helpers.connectivity.jwt.getUserDetailsFromToken
import com.example.testapp.model.AuthRequest
import com.example.testapp.repository.AccountRepository
import com.example.testapp.utils.SD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(private  val repo: AccountRepository) : ViewModel(){
    val authRequest = mutableStateOf(AuthRequest())
    val errorMsg = mutableStateOf("")
    val loading = mutableStateOf(false)
    fun login(home: () -> Unit = {}){
        errorMsg.value = ""
        loading.value = true
        Log.d("STS", SD.NetworkStatus.name)
        if(SD.NetworkStatus == IConnectivityObserver.ConnectivityStatus.Online){
            viewModelScope.launch {
                val resp = repo.login(authRequest.value)
                Log.d("API RESP",resp.toString())
                if(resp.isSuccess){
                    loading.value = false
                    AuthState.login(getUserDetailsFromToken(resp.result?.token!!))
                    home()
                }else{
                    loading.value = false
                    errorMsg.value = "Something went wrong"
                }

            }
        }else{
            loading.value = false
            errorMsg.value = "No Network Connectivity Found"
        }

    }

}