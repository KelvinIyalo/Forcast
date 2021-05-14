package com.example.forcast

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.forcast.data.WeatherApiServices
import com.example.forcast.data.db.ForcastDatabase
import com.example.forcast.data.network.NetworkDataSource
import com.example.forcast.data.network.NetworkDataSourceImpl
import com.example.forcast.data.network.response.ConnectivityInterceptor
import com.example.forcast.data.network.response.ConnectivityInterceptorImpl
import com.example.forcast.data.provider.LocationProvider
import com.example.forcast.data.provider.LocationProviderImpl
import com.example.forcast.data.provider.UnitProvider
import com.example.forcast.data.provider.UnitProviderImpl
import com.example.forcast.data.repository.ForcastRepository
import com.example.forcast.data.repository.ForcastRepositoryImpl
import com.example.forcast.ui.weather.current.CurrentWeatherViewModelFactory
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForcastApplication :Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForcastApplication))

        bind() from singleton { ForcastDatabase(instance()) }
        bind() from singleton { instance<ForcastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForcastDatabase>().weatherLocationDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherApiServices(instance()) }
        bind<NetworkDataSource>() with singleton { NetworkDataSourceImpl(instance()) }
        bind<ForcastRepository>() with singleton { ForcastRepositoryImpl(instance(),instance(),instance(),instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(),instance()) }
        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl(instance(),instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this,R.xml.prefrences,false)
    }
}