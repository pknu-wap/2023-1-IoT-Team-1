package com.example.fishfarmapplication.ui.main.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fishfarmapplication.ui.main.models.databases.AppDatabase
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraphDataViewModel(application: Application) : AndroidViewModel(application) {
    val Repository: Repository = Repository(AppDatabase.getDatabase(application, viewModelScope))
    var allWaterTemperatures : LiveData<List<WaterTemperatureEntity>> = Repository.allWaterTemperatures

    fun insert(entity: WaterTemperatureEntity) = viewModelScope.launch (Dispatchers.IO){
        Repository.insert(entity)
    }

    fun deleteAllWaterTemperature() = viewModelScope.launch(Dispatchers.IO){
        Repository.deleteAllWaterTemperature()
    }

    fun getAllWaterTemperature() : List<WaterTemperatureEntity>? {
        return allWaterTemperatures.value
    }


}