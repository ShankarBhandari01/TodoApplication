package com.shankar.todoapplication.base

import androidx.room.*
import kotlinx.coroutines.flow.Flow

interface BaseDao<T> {

    fun getById(ID: Int): T

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<T>)

    @Update
    suspend fun update(data: T)

    @Delete
    suspend fun delete()

}
