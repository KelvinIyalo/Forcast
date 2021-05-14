package com.example.forcast.data.network.response

import android.content.Context
import android.net.ConnectivityManager
import com.example.forcast.internal.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {
    val appContext = context.applicationContext


    override fun intercept(chain: Interceptor.Chain): Response {
 if (!isOnline())
     throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    fun isOnline():Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netWorkInfo = connectivityManager.activeNetworkInfo
 return netWorkInfo !=null && netWorkInfo.isConnected
    }
}