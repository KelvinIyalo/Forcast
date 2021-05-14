package com.example.forcast.data.provider

import com.example.forcast.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLoaction:WeatherLocation):Boolean
    suspend fun getPreferedLocationString():String
}