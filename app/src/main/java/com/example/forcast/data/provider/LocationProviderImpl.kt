package com.example.forcast.data.provider

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.example.forcast.data.db.entity.WeatherLocation
import com.example.forcast.internal.LocationPermissionNotGrantedException
import com.example.forcast.internal.asDeferred
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Deferred

const val USE_DEVICE_LOCATION = "USE_DEVICE_LOCATION"
const val CUSTOM_LOCATION = "CUSTOM_LOCATION"

class LocationProviderImpl(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    context: Context) : PreferenceProvider(context), LocationProvider {
    val appContext = context.applicationContext
    override suspend fun hasLocationChanged(lastWeatherLoaction: WeatherLocation): Boolean {
        val deviceLocaionChanged = try {
            hasDeviceLocationChanged(lastWeatherLoaction)
        } catch (e:LocationPermissionNotGrantedException){
            false
        }

        return deviceLocaionChanged || hasCustomLocationChanged(lastWeatherLoaction)
    }



    override suspend fun getPreferedLocationString(): String {
        if (isUsingDeviceLocation()){
            try {
                val deviceLocation = getLastDeviceLocation().await()
                    ?:return "${getCustomeLocationName()}"
                return "${deviceLocation.latitude}, ${deviceLocation.longitude}"
            } catch (e:LocationPermissionNotGrantedException){
                return "${getCustomeLocationName()}"
            }

        }
        else
            return "${getCustomeLocationName()}"
    }
    private suspend fun hasDeviceLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        if (!isUsingDeviceLocation())
            return false

        val deviceLocation = getLastDeviceLocation().await()
            ?: return false

        val comperisonThresholds = 0.03
        return Math.abs(deviceLocation.latitude - lastWeatherLocation.lat) > comperisonThresholds &&
                Math.abs(deviceLocation.longitude - lastWeatherLocation.lon) > comperisonThresholds

    }


    private fun hasCustomLocationChanged(lastWeatherLocation:WeatherLocation):Boolean{
        val CustomLocationName = getCustomeLocationName()
        return CustomLocationName != lastWeatherLocation.name

    }

    private fun isUsingDeviceLocation():Boolean{
        return prefrences.getBoolean(USE_DEVICE_LOCATION,true)
    }

    private fun getCustomeLocationName():String?{
        return prefrences.getString(CUSTOM_LOCATION,null)
    }

    private fun getLastDeviceLocation():Deferred<Location?>{
    fusedLocationProviderClient.lastLocation
        return if (hasLocationPermission())
            fusedLocationProviderClient.lastLocation.asDeferred()
        else
            throw LocationPermissionNotGrantedException()
    }


    private fun hasLocationPermission():Boolean{
        return ContextCompat.checkSelfPermission(appContext,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
}