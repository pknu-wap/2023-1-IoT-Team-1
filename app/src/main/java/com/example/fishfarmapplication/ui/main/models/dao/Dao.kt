package com.example.fishfarmapplication.ui.main.models.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureEntity

@androidx.room.Dao
interface Dao {
    @Query("select * from WaterTemperature")
    fun getAll() : LiveData<List<WaterTemperatureEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: WaterTemperatureEntity)

    @Query("delete from WaterTemperature")
    fun deleteAll()

    @Delete
    fun delete(entity: WaterTemperatureEntity)
}