package com.example.doubtnuttestapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

open class NetworkUtils {
    companion object {
        fun isNetworkConnected(context: Context?): Boolean {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnected == true
        }
    }
}