package com.example.forcast.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.threeten.bp.ZoneId
import org.threeten.bp.Instant
import org.threeten.bp.ZonedDateTime

const val WEATHER_LOCATION_ID = 0

@Entity(tableName = "weather_location")

data class WeatherLocation(
    val country: String, // United Kingdom
    val lat: Double, // 51.52
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Long, // 1597425802
    val lon: Double, // -0.11
    val name: String, // London
    val region: String, // City of London, Greater London
    @SerializedName("tz_id")
    val tzId: String // Europe/London
){
    @PrimaryKey(autoGenerate = false)
    var id:Int = WEATHER_LOCATION_ID

    val zonedDateTime:ZonedDateTime
    get() {
        val instant =Instant.ofEpochSecond(localtimeEpoch)
        val zoneId = ZoneId.of(tzId)
        return ZonedDateTime.ofInstant(instant,zoneId)
    }
}