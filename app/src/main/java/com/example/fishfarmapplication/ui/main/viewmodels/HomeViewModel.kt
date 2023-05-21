package com.example.fishfarmapplication.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Date

class HomeViewModel : ViewModel(){
    //true면 상태 좋음 false 상태 나쁨
    private var _homeStatus = MutableLiveData<Boolean>()

    val homeStatus : LiveData<Boolean> get() = _homeStatus

    private var _standard = MutableLiveData<Standard>()

//    private var _waterTemperatureStandard = MutableLiveData<Float>()
//    private var _phStandard = MutableLiveData<Float>()
//    private var _foodStandard = MutableLiveData<Float>()

    val standard : LiveData<Standard> get() = _standard

//    val waterTemperatureStandard : LiveData<Float> get() = _waterTemperatureStandard
//    val phStandard : LiveData<Float> get() = _phStandard
//    val foodStandard : LiveData<Float> get() = _foodStandard

    private var _waterTemperatureData =MutableLiveData<Float>()
    private var _phData = MutableLiveData<Float>()
    private var _foodData = MutableLiveData<Float>()

    val waterTemperatureData : LiveData<Float> get() = _waterTemperatureData
    val phData : LiveData<Float> get() = _phData
    val foodData : LiveData<Float> get() = _foodData

    private var _waterTemperatureStatus = MutableLiveData<Boolean>()
    private var _phStatus = MutableLiveData<Boolean>()
    private var _foodStatus = MutableLiveData<Boolean>()

    val waterTemperatureStatus :LiveData<Boolean> get() = _waterTemperatureStatus
    val phStatus : LiveData<Boolean> get() = _phStatus
    val foodStatus : LiveData<Boolean> get() = _foodStatus

    init {
        _homeStatus.value = true

//        _waterTemperatureStandard.value = 10F
//        _phStandard.value = 10F
//        _foodStandard.value = 4F

        _standard.value = Standard()

        _waterTemperatureData.value = 10F
        _phData.value = 10F
        _foodData.value = 4F

        val currentStandard = standard.value
        if(currentStandard != null){
            _waterTemperatureStatus.value = if(currentStandard.waterTemperature == waterTemperatureData.value) true else false
            _phStatus.value = if(currentStandard.ph == phData.value) true else false
            _foodStatus.value = if(currentStandard.food == foodData.value) true else false
        }

//        _waterTemperatureStatus.value = if(standard.value.waterTemperature == waterTemperatureData.value) true else false
//        _foodStatus.value = if(foodStandard.value == foodData.value) true else false
//        _phStatus.value = if(phStandard.value == phData.value) true else false

    }
    fun setHomeStatusValue(boolean: Boolean){
        _homeStatus.value = boolean
    }

    fun getHomeStatusValue() : Boolean?{
        return homeStatus.value
    }

    fun setWaterTemperatureStandard(float : Float){
        _standard.value!!.waterTemperature = float
    }

    fun setPhStandard(float: Float){
        _standard.value!!.ph = float
//        _phStandard.value = float
    }

    fun setFoodStandard(float: Float){
        _standard.value!!.food = float
    }

    fun setWaterTemperatureData(float: Float){
        _waterTemperatureData.value = float
    }

    fun setPhData(float:Float){
        _phData.value = float
    }

    fun setFoodData(float: Float){
        _foodData.value = float
    }

    fun setWaterTemperatureStatus(boolean: Boolean){
        _waterTemperatureStatus.value = boolean
    }

    fun setPhStatus(boolean: Boolean){
        _phStatus.value = boolean
    }

    fun setFoodStatus(boolean: Boolean){
        _foodStatus.value = boolean
    }

    data class Standard(var waterTemperature : Float = 10f, var ph : Float = 11f, var food : Float = 4f)





}