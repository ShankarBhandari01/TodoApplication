package com.shankar.todoapplication.database

import androidx.room.*
import com.shankar.todoapplication.model.CategoryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao : BaseDao<CategoryModel> {
    @Query("SELECT * FROM Tbl_category WHERE id =:ID ")
    override fun getById(ID: Int): Flow<CategoryModel>

    @Query("SELECT * FROM Tbl_category")
    override fun getAllList(): Flow<List<CategoryModel>>

    @Query("Delete  from Tbl_category")
    override suspend fun delete()
}

