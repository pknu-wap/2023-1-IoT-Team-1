package com.example.fishfarmapplication.ui.main.models.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fishfarmapplication.ui.main.models.entity.PhEntity
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureEntity

@Dao
interface AppDao {
    @Query("select * from WaterTemperature")
    fun getAllWaterTemperature() : LiveData<List<WaterTemperatureEntity>>

//    @Query("select * from Ph")
//    fun getAllPh() : LiveData<List<PhEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: WaterTemperatureEntity)

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insert(entity: PhEntity)

    @Query("delete from WaterTemperature")
    fun deleteAllWaterTemperature()

//    @Query("delete from Ph")
//    fun deleteAllPh()

}