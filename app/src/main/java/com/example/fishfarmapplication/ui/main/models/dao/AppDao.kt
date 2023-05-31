package com.example.fishfarmapplication.ui.main.models.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fishfarmapplication.ui.main.models.entity.AlarmEntity

import com.example.fishfarmapplication.ui.main.models.entity.GraphEntity
import com.example.fishfarmapplication.ui.main.models.entity.PhTuple
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureTuple


@Dao
interface AppDao {
    @Query("select time,watertemperature from GraphData order by time asc")
    fun getAllWaterTemperature() : LiveData<List<WaterTemperatureTuple>>

    @Query("select g.watertemperature from GraphData g where g.time = (select max(time) from GraphData)")
    fun getRecentWaterTemperature() : LiveData<Float>

    @Query("select time,ph from GraphData order by time asc")
    fun getAllPh() : LiveData<List<PhTuple>>

//    @Query("select * from Ph")
//    fun getAllPh() : LiveData<List<PhEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entity: GraphEntity)


    @Query("delete from GraphData")
    suspend fun deleteAll()

//    @Query("delete from Ph")
//    fun deleteAllPh()

//    @Query("select * from ActiveAlarms")
//    fun getAllAlarms() : List<AlarmEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE) // 알람은 중복되지 않게 저장
//    fun addAlarm(item: AlarmEntity)
//
//    @Query("DELETE FROM ActiveAlarms WHERE alarm_code = :alarm_code") // 알람 코드로 삭제
//    fun deleteAlarm(alarm_code: Int)

}