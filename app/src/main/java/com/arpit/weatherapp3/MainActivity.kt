package com.arpit.weatherapp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {Client.api.myWeather("delhi")}
            if (response.isSuccessful) {
                response.body()?.let {res->
                    res.weather?.let {

                    }
                    runOnUiThread{
                        temperature.text = "Temprature: "+ res.main?.temp.toString()
                        humidity.text = "Humidity: "+ res.main?.humidity.toString()
                        sunrise.text="Sunrise:  " + res.sys?.sunrise.toString()
                        sunset.text="Sunset:  " + res.sys?.sunset.toString()
                        wind.text="Wind:  " +res.wind?.speed.toString()
                        clouds.text="Clouds:  " + res.clouds?.all.toString()
                    }
                }
            }
        }

        btnSearch.setOnClickListener{
            responseWeather()
        }
    }

    private fun responseWeather() {
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {Client.api.myWeather(etText.text.toString())}
            if (response.isSuccessful){
                response.body()?.let {res ->
                    res.weather?.let {
                        runOnUiThread {
                            temperature.text = "Temprature: "+ res.main?.temp.toString()
                            humidity.text = "Humidity: "+ res.main?.humidity.toString()
                            sunrise.text="Sunrise:  " + res.sys?.sunrise.toString()
                            sunset.text="Sunset:  " + res.sys?.sunset.toString()
                            wind.text="Wind:  " +res.wind?.speed.toString()
                            clouds.text="Clouds:  " + res.clouds?.all.toString()
                        }
                    }

                }
            }
        }
    }
}