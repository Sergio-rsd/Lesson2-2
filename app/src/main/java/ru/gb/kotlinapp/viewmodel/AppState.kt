package ru.gb.kotlinapp.viewmodel

import ru.gb.kotlinapp.model.Weather

sealed class AppState {
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Throwable?) : AppState()
    object Loading : AppState()
}
