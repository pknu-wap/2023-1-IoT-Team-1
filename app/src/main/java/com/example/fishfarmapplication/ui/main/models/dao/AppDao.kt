package com.example.fishfarmapplication.ui.main.models.dao

import androidx.lifecycle.LiveData
import androidx.room.*

import com.example.fishfarmapplication.ui.main.models.entity.GraphEntity
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureTuple


@Dao
interface AppDao {
    @Query("select time,watertemperature from GraphData order by time asc")
    fun getAllWaterTemperature() : LiveData<List<WaterTemperatureTuple>>

//    @Query("select * from Ph")
//    fun getAllPh() : LiveData<List<PhEntity>>

    @Insert
    fun insertAll(entity: GraphEntity)


//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insert(entity: PhEntity)

    @Query("delete from GraphData")
    fun deleteAll()

//    @Query("delete from Ph")
//    fun deleteAllPh()

}