package com.example.forcast.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.forcast.data.provider.UnitProvider
import com.example.forcast.data.repository.ForcastRepository

class CurrentWeatherViewModelFactory(
    private val forecastRepository: ForcastRepository,
private val unitProvider: UnitProvider
):ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodayViewModel(forecastRepository,unitProvider) as T
    }
}