package com.example.fishfarmapplication.ui.main.models.databases

import android.content.Context
import androidx.room.*
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [WaterTemperatureEntity:: class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dao(): com.example.fishfarmapplication.ui.main.models.dao.Dao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                )   .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}