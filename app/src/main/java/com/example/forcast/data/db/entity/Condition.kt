package com.example.forcast.data.db.entity


import com.google.gson.annotations.SerializedName

data class Condition(
    val code: Int, // 1009
    val icon: String, // //cdn.weatherapi.com/weather/64x64/day/122.png
    val text: String // Overcast
)