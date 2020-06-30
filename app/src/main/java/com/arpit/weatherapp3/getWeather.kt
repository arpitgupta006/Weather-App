package com.arpit.weatherapp3

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.StringBuilder

interface getWeather {

    @GET("weather?appid=7bbeec176cbdcfbdce3b81e0b6cc14b7&units=Metric")
    suspend fun myWeather(@Query("q")q:String) : Response<WeatherResponse>

}