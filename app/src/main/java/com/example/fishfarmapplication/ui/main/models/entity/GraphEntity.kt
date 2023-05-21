package com.example.fishfarmapplication.ui.main.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date




@Entity(tableName = "GraphData")
data class GraphEntity(
    @ColumnInfo(name = "time") val time: Float,
    @ColumnInfo(name = "watertemperature") val temperature : Float,
    @ColumnInfo(name = "ph") val phData : Float,
    @ColumnInfo(name = "food") val food : Float
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


data class WaterTemperatureTuple(
    @ColumnInfo(name="time") val time: Float,
    @ColumnInfo(name="watertemperature") val temperature : Float
)

data class PhTuple(
    @ColumnInfo(name="time") val time: Float,
    @ColumnInfo(name = "ph") val phData: Float
)

