package com.example.fishfarmapplication.ui.main.viewmodels

import androidx.lifecycle.LiveData
import com.example.fishfarmapplication.ui.main.models.databases.AppDatabase
import com.example.fishfarmapplication.ui.main.models.entity.GraphEntity
import com.example.fishfarmapplication.ui.main.models.entity.PhTuple
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureTuple

class Repository(mDatabase: AppDatabase) {
    private val dao = mDatabase.dao()

    val allWaterTemperatures : LiveData<List<WaterTemperatureTuple>> = dao.getAllWaterTemperature()

    val allPh : LiveData<List<PhTuple>> = dao.getAllPh()

    val recentWaterTemperature : LiveData<Float> = dao.getRecentWaterTemperature()

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
    
    suspend fun insert(entity: GraphEntity){
        dao.insertAll(entity)
    }

    suspend fun deleteWaterTemperature(entity: GraphEntity){

    }
    suspend fun deleteAll(){
        dao.deleteAll()
    }

}