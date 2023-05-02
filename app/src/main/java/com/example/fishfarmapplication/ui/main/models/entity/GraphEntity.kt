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

