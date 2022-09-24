package com.example.weather_forecast_api

data class WeatherModel(
    val name: String,
    val country: String,
    val localtime: String,
    val temp_c: String,
    val temp_f: String
)