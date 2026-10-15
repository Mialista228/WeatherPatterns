package uz.ttpu.weatherpatterns.mvc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import uz.ttpu.weatherpatterns.di.WeatherModule
import uz.ttpu.weatherpatterns.mvc.controller.WeatherController
import uz.ttpu.weatherpatterns.mvc.model.WeatherModel
import uz.ttpu.weatherpatterns.ui.WeatherScreen

class MvcActivity : ComponentActivity(), WeatherView {

    private val model = WeatherModel(WeatherModule.repository)
    private val controller = WeatherController(model, view = this)
    private var status by mutableStateOf("Pick a city")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                WeatherScreen(status = status, onCitySelected = controller::onCitySelected)
            }
        }
    }

    // why does render() take no parameters, and what would change if the Controller passed the Weather into the View instead?
    // Answer: render() takes no parameters because in MVC the View reads the Model directly ("pull" approach).
    // If the Controller passed the data directly, the View would become dependent on the Controller/parameters, breaking the direct Model-to-View reading pattern....
    override fun render() {
        status = when {
            model.isLoading -> "Loading…"
            model.error != null -> "Error: ${model.error}"
            model.weather != null -> {
                val w = model.weather!!
                "${w.city}: ${w.temperatureC}°C, ${w.condition}"
            }
            else -> "Pick a city"
        }
    }
}