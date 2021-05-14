package com.example.forcast.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.forcast.data.WeatherApiServices
import com.example.forcast.data.network.response.CurrentWeatherResponse
import com.example.forcast.internal.NoConnectivityException

class NetworkDataSourceImpl(private val weatherApiServices: WeatherApiServices) : NetworkDataSource {
    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun FetchCurrentWeather(location: String, languageCode: String) {
       try {
 val fetchCurrentWeather = weatherApiServices
     .getCurrentWeather(location,languageCode)
     .await()
     _downloadedCurrentWeather.postValue(fetchCurrentWeather)
       }
       catch (e: NoConnectivityException){
           Log.e("Connectivity", "No Internet Connection", e)
       }
    }
}