package com.example.forcast.data.db.unitLocalized

import androidx.room.ColumnInfo

data class ImperialCurrentWeatherEntry (
    @ColumnInfo(name = "tempF")
    override val temperature: Double,
    @ColumnInfo(name = "windMph")
    override val windSpeed: Double,
    @ColumnInfo(name = "precipIn")
    override val precipitationVolume: Double,
    @ColumnInfo(name = "feelslikeF")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name = "visMiles")
    override val visibilityDistance: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,
    @ColumnInfo(name = "windDir")
    override val windDirection: String

):UnitSpecificCurrentWeatherEntry