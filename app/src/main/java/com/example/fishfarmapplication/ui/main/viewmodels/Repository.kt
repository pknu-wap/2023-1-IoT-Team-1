package com.example.fishfarmapplication.ui.main.viewmodels

import androidx.lifecycle.LiveData
import com.example.fishfarmapplication.ui.main.models.databases.AppDatabase
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureEntity

class Repository(mDatabase: AppDatabase) {
    private val dao = mDatabase.dao()

    val allWaterTemperatures : LiveData<List<WaterTemperatureEntity>> = dao.getAllWaterTemperature()

    companion object{
        private var sInstance : Repository? = null
        fun getInstance(database: AppDatabase) : Repository{
            return sInstance
                ?: synchronized(this){
                    val instance = Repository(database)
                    sInstance = instance
                    instance
                }
        }
    }
    
    suspend fun insert(entity: WaterTemperatureEntity){
        dao.insert(entity)
    }

    suspend fun deleteWaterTemperature(entity: WaterTemperatureEntity){

    }
    suspend fun deleteAllWaterTemperature(){
        dao.deleteAllWaterTemperature()
    }

}