package com.example.fishfarmapplication.ui.main.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ActiveAlarms")
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true) var serialNum: Int, // 일련 번호
    var alarm_code : Int, // 알람 요청코드
    var time : String, // 시간
    var content : String // 알람 내용
)

