package com.example.forcast.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.forcast.data.db.entity.CurrentWeatherEntry
import com.example.forcast.data.db.entity.WeatherLocation

@Database(
    entities = [CurrentWeatherEntry::class, WeatherLocation::class],
    version = 1
)
abstract class ForcastDatabase:RoomDatabase() {
    abstract fun currentWeatherDao():CurrentWeatherDao
    abstract fun weatherLocationDao():WeatherLocationDao

    companion object{
     @Volatile   private var instance:ForcastDatabase?= null
        private val LOCK = Any()

        operator fun invoke(context:Context) = instance ?: synchronized(LOCK){
            instance ?: bildDatabase(context).also {
                instance = it
            }
        }
private fun bildDatabase(context: Context) =
    Room.databaseBuilder(context.applicationContext, ForcastDatabase::class.java, "forecast.db").build()
    }

}