package com.example.weatherapp.network.forecast

import com.example.weatherapp.network.current.Condition

data class Day(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val condition: Condition
    ) {

}
