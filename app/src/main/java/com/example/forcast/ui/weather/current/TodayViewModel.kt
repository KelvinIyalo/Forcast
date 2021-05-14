package com.example.forcast.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.forcast.data.provider.UnitProvider
import com.example.forcast.data.repository.ForcastRepository
import com.example.forcast.internal.UnitSystem
import com.example.forcast.internal.lazyDeferred

class TodayViewModel(
    private val forecastRepository: ForcastRepository,
 unitProvider :UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
    get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCUrrentWeather(isMetric)
    }
    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}
