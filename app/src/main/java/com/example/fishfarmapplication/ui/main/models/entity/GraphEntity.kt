package com.example.fishfarmapplication.ui.main.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "WaterTemperature")
data class WaterTemperatureEntity (
    @ColumnInfo(name = "time") val time: Float,
    @ColumnInfo(name = "waterTemperature") val temperature : Float,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Entity(tableName = "Ph")
data class PhEntity(
    @ColumnInfo(name="time") val time:Float,
    @ColumnInfo(name = "Ph") val ph:Float,
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0;
}
