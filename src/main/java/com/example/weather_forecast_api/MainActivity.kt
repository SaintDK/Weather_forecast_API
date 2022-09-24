package com.example.weather_forecast_api

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weather_forecast_api.databinding.ActivityMainBinding
import org.json.JSONObject
import kotlin.math.log


const val API_KEY = "0d024130c2a641d1b36183258221709"
class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

        val input_text = findViewById<EditText>(R.id.input_text)
        val bStart = findViewById<Button>(R.id.bStart)

        bStart.setOnClickListener{

            val userInput = input_text.text.toString()
            Toast.makeText(this, userInput, Toast.LENGTH_SHORT).show()

            getResult(userInput)

        }

    }
    private fun getResult(userInput: String){

        val url = "http://api.weatherapi.com/v1/current.json?key=$API_KEY&q=$userInput&aqi=no"
        val qoeue = Volley.newRequestQueue(this)
        val stingRequest = StringRequest(Request.Method.GET,

            url,

            { result -> parseWeatherData(result)

//                Log.d("MyLog", "Response: $userInput : $response")
            },

            {
                Log.d("MyLog", "Error: $it")
            }

        )
        qoeue.add(stingRequest)

    }

    private fun parseWeatherData(result: String){
        val mainObject = JSONObject(result)
        val item = WeatherModel(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("location").getString("country"),
            mainObject.getJSONObject("location").getString("localtime"),
            mainObject.getJSONObject("current").getString("temp_c"),
            mainObject.getJSONObject("current").getString("temp_f")
        )
        Log.d("MyLog", "City: ${item.name}")
        Log.d("MyLog", "City: ${item.country}")
        Log.d("MyLog", "City: ${item.localtime}")
        Log.d("MyLog", "City: ${item.temp_c}")
        Log.d("MyLog", "City: ${item.temp_f}")
    }

}

