package com.example.weatherapp.network.forecast

import com.example.weatherapp.network.current.CurrentWeatherProperty

data class ForecastWeatherProperty(
    val currentWeatherProperty: CurrentWeatherProperty,
    val forecast: ForecastDay
) {

}
