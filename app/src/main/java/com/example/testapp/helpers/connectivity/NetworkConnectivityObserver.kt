package com.example.testapp.helpers.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.core.content.getSystemService
import com.example.testapp.utils.SD
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkConnectivityObserver(private val context: Context) : IConnectivityObserver {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    override fun observe(): Flow<IConnectivityObserver.ConnectivityStatus> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback(){
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    SD.NetworkStatus = IConnectivityObserver.ConnectivityStatus.Online;
                    launch { send(IConnectivityObserver.ConnectivityStatus.Online) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    SD.NetworkStatus = IConnectivityObserver.ConnectivityStatus.Losing;
                    launch { send(IConnectivityObserver.ConnectivityStatus.Losing) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    SD.NetworkStatus = IConnectivityObserver.ConnectivityStatus.Lost;
                    launch { send(IConnectivityObserver.ConnectivityStatus.Lost) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    SD.NetworkStatus = IConnectivityObserver.ConnectivityStatus.Offline;
                    launch { send(IConnectivityObserver.ConnectivityStatus.Offline) }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose{
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()

    }
}