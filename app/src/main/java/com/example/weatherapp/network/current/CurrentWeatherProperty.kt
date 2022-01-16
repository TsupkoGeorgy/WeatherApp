package com.example.weatherapp.network.current


data class CurrentWeatherProperty(
    val location: Location,
    val current: CurrentWeather
) {
}