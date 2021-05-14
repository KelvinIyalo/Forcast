package com.example.forcast.data.db.unitLocalized

interface UnitSpecificCurrentWeatherEntry {
    val temperature: Double
    val windSpeed: Double
    val precipitationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
    val conditionText: String
    val conditionIconUrl: String
    val windDirection: String
}