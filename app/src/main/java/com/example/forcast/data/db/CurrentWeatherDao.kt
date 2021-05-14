package com.example.forcast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.forcast.data.db.entity.CURRENT_WEATHER_ID
import com.example.forcast.data.db.entity.CurrentWeatherEntry
import com.example.forcast.data.db.unitLocalized.ImperialCurrentWeatherEntry
import com.example.forcast.data.db.unitLocalized.MetricCurrentWeatherEntry
import com.example.forcast.data.db.unitLocalized.UnitSpecificCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry:CurrentWeatherEntry )

    @Query("select * from current_weather where id= $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id= $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}