package com.example.forcast.data.repository

import androidx.lifecycle.LiveData
import com.example.forcast.data.db.CurrentWeatherDao
import com.example.forcast.data.db.WeatherLocationDao
import com.example.forcast.data.db.entity.WeatherLocation
import com.example.forcast.data.db.unitLocalized.UnitSpecificCurrentWeatherEntry
import com.example.forcast.data.network.NetworkDataSource
import com.example.forcast.data.network.response.CurrentWeatherResponse
import com.example.forcast.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForcastRepositoryImpl(
    private val currentWeatherDao:CurrentWeatherDao,
private val weathernetwworkDataSource: NetworkDataSource,
private val weatherLocationDao: WeatherLocationDao,
private val locationProvider: LocationProvider
) : ForcastRepository {
    init {
        weathernetwworkDataSource.downloadedCurrentWeather.observeForever{ newCurrentWeather ->
        persistFetchedCurrentWeather(newCurrentWeather)

        }
    }
    override suspend fun getCUrrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {

   return withContext(Dispatchers.IO){
       initData()
    return@withContext if (metric) currentWeatherDao.getWeatherMetric()
       else currentWeatherDao.getWeatherImperial()
        }

    }

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO){
            return@withContext weatherLocationDao.getLocation()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {  currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        weatherLocationDao.upsert(fetchedWeather.location)
        }

    }

    private suspend fun initData(){
        val lastWeatherLocation = weatherLocationDao.getLocation().value

        if (lastWeatherLocation == null || locationProvider.hasLocationChanged(lastWeatherLocation)){
            fetchCurrentWeather()
            return
        }

    if (isFetchedCurrentNeeded(lastWeatherLocation.zonedDateTime))
        fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weathernetwworkDataSource.FetchCurrentWeather(
            locationProvider.getPreferedLocationString(),
            Locale.getDefault().language
        )
    }


    private fun isFetchedCurrentNeeded(lastFetchTime:ZonedDateTime): Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

}