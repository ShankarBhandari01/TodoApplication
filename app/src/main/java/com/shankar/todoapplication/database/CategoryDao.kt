package com.shankar.todoapplication.database

import androidx.room.*
import com.shankar.todoapplication.base.BaseDao
import com.shankar.todoapplication.base.UiState
import com.shankar.todoapplication.model.CategoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface CategoryDao : BaseDao<CategoryModel> {
    @Query("SELECT * FROM Tbl_category WHERE id =:ID ")
    override fun getById(ID: Int): CategoryModel

    @Query("SELECT * FROM Tbl_category")
    fun getAllList(): Flow<List<CategoryModel>>

    @Query("Delete from Tbl_category")
    override suspend fun delete()
}

