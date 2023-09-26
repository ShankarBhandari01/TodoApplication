package com.shankar.todoapplication.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room

object DatabaseManager {
    private var database: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }

    private fun buildDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}