package com.example.fishfarmapplication.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Date

class HomeViewModel : ViewModel(){
    //true면 상태 좋음 false 상태 나쁨
    private var _homeStatus = MutableLiveData<Boolean>()
    private var _waterTemperatureStandard = MutableLiveData<Float>()
    private var _phStandard = MutableLiveData<Float>()
    private var _foodStandard = MutableLiveData<Float>()

    val homeStatus : LiveData<Boolean> get() = _homeStatus
    val waterTemperatureStandard : LiveData<Float> get() = _waterTemperatureStandard
    val phStandard : LiveData<Float> get() = _phStandard
    val foodStandard : LiveData<Float> get() = _foodStandard

    init {
        _homeStatus.value = false
        _waterTemperatureStandard.value = 10F
        _phStandard.value = 10F
        _foodStandard.value = 4F
    }

    fun setHomeStatusValue(boolean: Boolean){
        _homeStatus.value = boolean
    }

    fun getHomeStatusValue() : Boolean?{
        return homeStatus.value
    }

    fun setWaterTemperatureStandard(float : Float){
        _waterTemperatureStandard.value = float
    }





}