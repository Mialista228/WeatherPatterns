package uz.ttpu.weatherpatterns.di

import uz.ttpu.weatherpatterns.data.api.FakeWeatherApi
import uz.ttpu.weatherpatterns.data.repository.WeatherRepository

object WeatherModule {
    // One shared Model layer for all four patterns.
    // 3 seconds of fake latency leaves time to rotate the device in the experiments.
    val repository = WeatherRepository(FakeWeatherApi(latencyMs = 3_000))
}
