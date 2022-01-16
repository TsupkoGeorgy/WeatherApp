package com.example.weatherapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.network.current.CurrentWeatherProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private val _properties = MutableLiveData<CurrentWeatherProperty>()

    val properties: LiveData<CurrentWeatherProperty>
        get() = _properties


    val displayPropertyCurrentConditionText = MutableLiveData<String>()

    private val _displayPropertyCurrentFeelslikeTemperatureInCelsius = MutableLiveData<Double>()
    val displayPropertyCurrentFeelslikeTemperatureInCelsius: LiveData<Double>
        get() = _displayPropertyCurrentFeelslikeTemperatureInCelsius

    val displayPropertyLocationRegion = MutableLiveData<String>()
    val displayPropertyLocationCityName = MutableLiveData<String>()
    val displayPropertyLocalTime = MutableLiveData<String>()


    private val _displayPropertyCurrentTemperatureInCelsius = MutableLiveData<Double>()
    val displayPropertyCurrentTemperatureInCelsius: LiveData<Double>
        get() = _displayPropertyCurrentTemperatureInCelsius


    private val _displayPropertyCurrentWind = MutableLiveData<Double>()
    val displayPropertyCurrentWind: LiveData<Double>
        get() = _displayPropertyCurrentWind

    private val _displayPropertyCurrentHumidity = MutableLiveData<Double>()
    val displayPropertyCurrentHumidity: LiveData<Double>
        get() = _displayPropertyCurrentHumidity

    private val _displayPropertyCurrentAirPressure = MutableLiveData<Double>()
    val displayPropertyCurrentAirPressure: LiveData<Double>
        get() = _displayPropertyCurrentAirPressure

    init {
        getWeatherProperties()
    }

    private fun getWeatherProperties() {
        coroutineScope.launch {
            val getPropertiesDeferred = WeatherApi.retrofitService.getCurrentPropertiesAsync("Novocherkassk")
            try {
                val propertyResult = getPropertiesDeferred.await()
                _properties.value = propertyResult
                //_response.value = propertyResult.current.temp_c
                displayPropertyLocationRegion.value = _properties.value!!.location.region
                displayPropertyLocationCityName.value = _properties.value!!.location.name
                displayPropertyLocalTime.value = _properties.value!!.location.localtime

                _displayPropertyCurrentTemperatureInCelsius.value =
                    _properties.value!!.current.temp_c
                _displayPropertyCurrentFeelslikeTemperatureInCelsius.value =
                    _properties.value!!.current.feelslike_c
                displayPropertyCurrentConditionText.value =
                    _properties.value!!.current.condition.text


                _displayPropertyCurrentWind.value = _properties.value!!.current.wind_kph
                _displayPropertyCurrentHumidity.value = _properties.value!!.current.humidity
                _displayPropertyCurrentAirPressure.value = _properties.value!!.current.pressure_mb


            } catch (e: Exception) {
                //_properties.value = ArrayList()
                _response.value = "err"
            }
//        WeatherApi.retrofitService.getProperties().enqueue(object : Callback<CurrentWeatherProperty> {
//            override fun onResponse(call: Call<CurrentWeatherProperty>, response: Response<CurrentWeatherProperty>) {
//                _response.value = "Success: ${response.body()!!.location.name}"
//            }
//
//            override fun onFailure(call: Call<CurrentWeatherProperty>, t: Throwable) {
//                _response.value = "Failure: " + t.message
//            }
//        })
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}