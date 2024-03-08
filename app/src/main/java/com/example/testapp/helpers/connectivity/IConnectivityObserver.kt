package com.example.testapp.helpers.connectivity

import kotlinx.coroutines.flow.Flow

interface IConnectivityObserver {
    fun observe() : Flow<ConnectivityStatus>

    enum class ConnectivityStatus{
        Online,Offline,Losing,Lost
    }
}