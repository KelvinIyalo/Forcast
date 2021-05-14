package com.example.forcast.data.network.response

import com.example.forcast.data.db.entity.CurrentWeatherEntry
import com.example.forcast.data.db.entity.WeatherLocation
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: WeatherLocation

)