package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Context

fun convertWindToFormatted(res: Context, windKPH: Double): String {
    var windToMS = windKPH * 0.28
    return res.getString(R.string.display_wind_to_ms, windToMS.toInt())
}

fun convertTemperatureToCelsiusFormatted(res: Context, temperature: Double): String {
    return res.getString(R.string.display_temperature_celsius, temperature.toInt())
}

fun convertHumidityToFormatted(res: Context, humidity: Double): String {
    return res.getString(R.string.display_humidity, humidity.toInt())
}

fun convertAirPressureToFormatted(res: Context, airPressure: Double): String {
    var airPressureToMMHG = airPressure / 1.333
    return res.getString(R.string.display_air_pressure, airPressureToMMHG.toInt())
}

fun convertFeelslikeToFormatted(res: Context, feelslike: Double):String {
    return res.getString(R.string.display_feelslike_temperature, feelslike.toInt())

}