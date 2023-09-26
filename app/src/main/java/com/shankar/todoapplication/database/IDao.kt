package com.shankar.todoapplication.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface IDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<T>)

    @Update
    suspend fun update(data: T)

    @Delete
    suspend fun delete(data: T)

    suspend fun getById(ID:Int): Flow<T>
}
