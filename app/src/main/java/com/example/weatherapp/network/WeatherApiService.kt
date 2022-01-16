package com.example.weatherapp.network

import com.example.weatherapp.network.current.CurrentWeather
import com.example.weatherapp.network.current.CurrentWeatherProperty
import com.example.weatherapp.network.forecast.ForecastWeatherProperty
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//"http://api.weatherapi.com/v1/"
// Current weather request http://api.weatherapi.com/v1/current.json?key=e3d29449941649ca8d4204238220801&q=Moscow&aqi=no
private const val BASE_URL = "https://api.weatherapi.com/v1/"

const val KEY = "e3d29449941649ca8d4204238220801"

private val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    //"current.json?key=$KEY&q=Novocherkassk"
    @GET("current.json?key=$KEY")
    fun getCurrentPropertiesAsync(
        @Query("q") city: String
    ): Deferred<CurrentWeatherProperty>

    @GET("forecast.json?key=$KEY")
    fun getForecastPropertiesAsync(
        @Query("q") city: String,
        @Query("days") days: Int,
    ): Deferred<ForecastWeatherProperty>

}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}

