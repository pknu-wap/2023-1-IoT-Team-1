package com.example.fishfarmapplication.ui.main.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fishfarmapplication.ui.main.models.databases.AppDatabase
import com.example.fishfarmapplication.ui.main.models.entity.GraphEntity
import com.example.fishfarmapplication.ui.main.models.entity.PhTuple
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureTuple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraphDataViewModel(application: Application) : AndroidViewModel(application) {
    val Repository: Repository = Repository(AppDatabase.getDatabase(application, viewModelScope))
    var allWaterTemperatures : LiveData<List<WaterTemperatureTuple>> = Repository.allWaterTemperatures

    val allPh : LiveData<List<PhTuple>> = Repository.allPh

    fun insertAll(entity: GraphEntity) = viewModelScope.launch (Dispatchers.IO){
        Repository.insert(entity)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO){
        Repository.deleteAll()
    }

    fun getAllWaterTemperature() : List<WaterTemperatureTuple>? {
        return allWaterTemperatures.value
    }

    fun getAllPh() : List<PhTuple>?{
        return allPh.value
    }


}