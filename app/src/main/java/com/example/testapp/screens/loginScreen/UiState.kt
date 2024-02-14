package com.example.testapp.screens.loginScreen

sealed class UiState {
    data object Loading : UiState()
    //data class Success(val stockList: List<Stock>) : UiState()
    data class Error(val message: String) : UiState()
}