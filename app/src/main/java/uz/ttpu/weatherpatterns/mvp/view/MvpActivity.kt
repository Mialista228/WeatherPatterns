package uz.ttpu.weatherpatterns.mvp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import uz.ttpu.weatherpatterns.ui.WeatherScreen

class MvpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                WeatherScreen(
                    status = "MVP Screen",
                    onCitySelected = { city ->

                    }
                )
            }
        }
    }
}