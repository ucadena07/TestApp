package com.example.testapp.screens.homeScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.authentication.AuthState
import com.example.testapp.helpers.connectivity.NetworkConnectivityObserver
import com.example.testapp.helpers.connectivity.jwt.getUserDetailsFromToken
import com.example.testapp.model.Buyer
import com.example.testapp.repository.AccountRepository
import com.example.testapp.repository.BuyerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel  @Inject constructor(private  val repo: BuyerRepository) : ViewModel() {
    val buyers = mutableStateOf<List<Buyer>?>(emptyList())

    init {

     getBuyers()
    }
    fun getBuyers(home: () -> Unit = {}){

        viewModelScope.launch {
            val resp = repo.getBuyers()
            buyers.value = resp.result
            Log.d("API RESP",resp.toString())
            if(resp.isSuccess){
                Log.d("Buyer",resp.result.toString())
            }else{

            }

        }

    }
}