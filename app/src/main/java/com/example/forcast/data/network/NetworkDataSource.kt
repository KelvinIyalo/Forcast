package com.example.forcast.data.network

import androidx.lifecycle.LiveData
import com.example.forcast.data.network.response.CurrentWeatherResponse

interface NetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    suspend fun FetchCurrentWeather(
        location: String,
        languageCode: String = "en"
    )
}