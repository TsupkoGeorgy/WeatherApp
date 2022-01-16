package com.example.weatherapp.network.current

import com.squareup.moshi.Json

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    @Json(name = "tz_id")
    val timeZone: String,
    @Json(name ="localtime_epoch" )
    val localtimeInUnixTime: Int,
    val localtime: String
    ) {

}
