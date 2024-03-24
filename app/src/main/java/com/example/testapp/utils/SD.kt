package com.example.testapp.utils

import com.example.testapp.helpers.connectivity.IConnectivityObserver

object SD {
     const val BaseUrl = "https://camposbrothersfarms-qa.netkinetix.com/"
     var NetworkStatus: IConnectivityObserver.ConnectivityStatus = IConnectivityObserver.ConnectivityStatus.Offline
}