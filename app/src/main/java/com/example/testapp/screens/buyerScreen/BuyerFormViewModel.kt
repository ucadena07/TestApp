package com.example.testapp.screens.buyerScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.dto.BuyerDTO
import com.example.testapp.repository.BuyerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuyerFormViewModel @Inject constructor(private  val repo: BuyerRepository) : ViewModel() {
    val buyerDTO = mutableStateOf<BuyerDTO?>(null)


    suspend fun get(id: Int){
        viewModelScope.launch {
            val resp = repo.get(id)
            if (resp.isSuccess){
                Log.d("API RESP",resp.result.toString())
                buyerDTO.value = resp.result
            }
        }
    }
}