package com.example.forcast.data.repository

import androidx.lifecycle.LiveData
import com.example.forcast.data.db.entity.WeatherLocation
import com.example.forcast.data.db.unitLocalized.UnitSpecificCurrentWeatherEntry

interface ForcastRepository {
    suspend fun getCUrrentWeather(metric:Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation():LiveData<WeatherLocation>
}