package com.example.weatherapp

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUrl = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUrl)
            .into(imgView)
    }
}

@BindingAdapter("setTemperatureInCelsius")
fun TextView.setCurrentTemperatureInCelsius(item: Double?) {
    item?.let {
        text = convertTemperatureToCelsiusFormatted(context, item)
    }
}

@BindingAdapter("setWind")
fun TextView.setWind(item: Double?) {
    item?.let {
        text = convertWindToFormatted(context, item)
    }
}


@BindingAdapter("setHumidity")
fun TextView.setHumidity(item: Double?) {
    item?.let {
        text = convertHumidityToFormatted(context, item)
    }
}

@BindingAdapter("setAirPressure")
fun TextView.setAirPressure(item: Double?) {
    item?.let {
        text = convertAirPressureToFormatted(context, item)
    }
}

@BindingAdapter("setFeelslikeTemperature")
fun TextView.setFeelslikeTemperature(item: Double?) {
    item?.let {
        text = convertFeelslikeToFormatted(context, item)
    }
}


