package com.example.testapp.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
open class BaseViewModel<T> : ViewModel() {
    fun uiState(): MutableState<T?> = uiState
    protected val uiState: MutableState<T?> = mutableStateOf(null)
}

