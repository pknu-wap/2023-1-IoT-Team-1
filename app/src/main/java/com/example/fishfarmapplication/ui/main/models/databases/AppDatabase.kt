package com.example.fishfarmapplication.ui.main.models.databases

import android.content.Context
import androidx.room.*
import com.example.fishfarmapplication.ui.main.models.dao.AppDao
import com.example.fishfarmapplication.ui.main.models.entity.GraphEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [GraphEntity:: class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                )   .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}