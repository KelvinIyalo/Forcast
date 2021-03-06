package com.example.forcast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.forcast.data.db.entity.CURRENT_WEATHER_ID
import com.example.forcast.data.db.entity.WEATHER_LOCATION_ID
import com.example.forcast.data.db.entity.WeatherLocation
import com.example.forcast.data.db.unitLocalized.ImperialCurrentWeatherEntry
import com.example.forcast.data.db.unitLocalized.MetricCurrentWeatherEntry

@Dao
interface WeatherLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherLocation: WeatherLocation)

    @Query("select * from weather_location where id= $WEATHER_LOCATION_ID")
    fun getLocation(): LiveData<WeatherLocation>
    
}