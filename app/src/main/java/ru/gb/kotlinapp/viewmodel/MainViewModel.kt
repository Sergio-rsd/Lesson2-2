package ru.gb.kotlinapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.gb.kotlinapp.model.Repository
import ru.gb.kotlinapp.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSource() = getDataFromLocalSource()
    fun getWeatherFromRemoteSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading

        Thread {
            sleep(2000)

            if (getRandomSuccess()) {
                liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))
            } else {
                liveDataToObserve.postValue(AppState.Error(error = null ))
            }

        }.start()

    }
// random access
    private fun getRandomSuccess(): Boolean {
        return (Math.random() * 2).toInt() != 0
    }

}