package com.example.forcast.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

import com.example.forcast.R
import com.example.forcast.internal.glide.ForecastAppGlideModule
import com.example.forcast.internal.glide.GlideApp
import com.example.forcast.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class TodayFragment1 : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
       //  get() = TODO("Not yet implemented")
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()


    private lateinit var viewModel: TodayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodayViewModel::class.java)

        bindUI()
    }

private fun bindUI() = launch{
    val currentWeather = viewModel.weather.await()
    val weatherLocation = viewModel.weatherLocation.await()

    weatherLocation.observe(viewLifecycleOwner, Observer {location ->
        if (location == null)
            return@Observer
        UpdateLocation(location.name)

    })

    currentWeather.observe(viewLifecycleOwner, Observer {
        if (it == null) return@Observer
        group_progress.visibility = View.GONE
        UpdateDateToToday()
        UpdateTemperature(it.temperature,it.feelsLikeTemperature)
        UpdateCondition(it.conditionText)
        UpdatePrecipitation(it.precipitationVolume)
        UpdateVisibiliy(it.visibilityDistance)
        UpdateWind(it.windDirection, it.windSpeed)
        GlideApp.with(this@TodayFragment1).load("https:${it.conditionIconUrl}").into(Image_condition)
    })
}
    private fun ChoosesLocalizationUnitAbbreviation(metric:String,imperial:String):String{
        return if (viewModel.isMetric) metric else imperial
    }
    private fun UpdateLocation(location:String){
        (activity as? AppCompatActivity)?.supportActionBar?.title = location

    }
    private fun UpdateDateToToday(){
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"

    }

    private fun UpdateTemperature(temperature:Double, feelslike:Double){
        val unitAbbreviation = ChoosesLocalizationUnitAbbreviation("°C", "°F")
        text_temperature.text = "$temperature$unitAbbreviation"
        temperature_feels_like.text = "Feels Like: $feelslike$unitAbbreviation"
    }
    private fun UpdateCondition(condition:String){
        text_condition.text = condition
    }


    private fun UpdatePrecipitation(precipitationVolume:Double){
        val unitAbbreviation = ChoosesLocalizationUnitAbbreviation("mm", "in")
        text_precipitation.text = "Precipitation: $precipitationVolume$unitAbbreviation"
    }

    private fun UpdateWind(windDirection:String,windSpeed:Double){
        val unitAbbreviation = ChoosesLocalizationUnitAbbreviation("kph", "mph")
        text_wind.text = "Wind: $windDirection, $windSpeed$unitAbbreviation"
    }
    private fun UpdateVisibiliy(visibityDistance:Double){
        val unitAbbreviation = ChoosesLocalizationUnitAbbreviation("mm", "mi")
        text_visibility.text = "Visibility: $visibityDistance$unitAbbreviation"
    }


}
