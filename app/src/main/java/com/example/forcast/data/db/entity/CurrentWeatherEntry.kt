package com.example.forcast.data.db.entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.forcast.data.db.entity.Condition
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "Current_weather")
data class CurrentWeatherEntry(
    @Embedded(prefix = "condition_")
    val condition: Condition,
    @SerializedName("feelslike_c")
    val feelslikeC: Double, // 19
    @SerializedName("feelslike_f")
    val feelslikeF: Double, // 66.2
    @SerializedName("gust_kph")
    val gustKph: Double, // 10.1
    @SerializedName("gust_mph")
    val gustMph: Double, // 6.3
    @SerializedName("is_day")
    val isDay: Int, // 1
    @SerializedName("precip_in")
    val precipIn: Double, // 0.01
    @SerializedName("precip_mm")
    val precipMm: Double, // 0.2
    @SerializedName("temp_c")
    val tempC: Double, // 19
    @SerializedName("temp_f")
    val tempF: Double, // 66.2
    val uv: Int, // 4
    @SerializedName("vis_km")
    val visKm: Int, // 8
    @SerializedName("vis_miles")
    val visMiles: Int, // 4
    @SerializedName("wind_dir")
    val windDir: String, // WNW
    @SerializedName("wind_kph")
    val windKph: Double, // 0
    @SerializedName("wind_mph")
    val windMph: Double // 0
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}