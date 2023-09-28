package com.shankar.todoapplication.database

import androidx.room.*
import com.shankar.todoapplication.model.CategoryModel

@Database(
    entities = [CategoryModel::class], version = 2, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}