package com.example.testapp.utils

import android.Manifest
import com.example.testapp.helpers.connectivity.IConnectivityObserver

object SD {
     const val BaseUrl = "https://camposbrothersfarms-qa.netkinetix.com/"
     var NetworkStatus: IConnectivityObserver.ConnectivityStatus = IConnectivityObserver.ConnectivityStatus.Offline
     val CAMERAX_PERMISSIONS = arrayOf(
          Manifest.permission.CAMERA,
          Manifest.permission.RECORD_AUDIO,
     )
}

