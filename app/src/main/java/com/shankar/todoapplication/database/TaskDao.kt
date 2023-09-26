package com.shankar.todoapplication.database

import androidx.room.Dao
import androidx.room.Query
import com.shankar.todoapplication.model.CategoryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao : IDao<CategoryModel> {
    @Query("SELECT * FROM Tbl_category WHERE id =:ID ")
    override suspend fun getById(ID: Int): Flow<CategoryModel>
}