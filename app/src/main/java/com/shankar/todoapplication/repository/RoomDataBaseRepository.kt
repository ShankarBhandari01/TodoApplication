package com.shankar.todoapplication.repository

import androidx.room.Transaction
import com.shankar.todoapplication.base.UiState
import com.shankar.todoapplication.database.CategoryDao
import com.shankar.todoapplication.model.CategoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow


class RoomDataBaseRepository(private val dao: CategoryDao) {

    fun getById(ID: Int): Flow<CategoryModel> = flow {
        emit(dao.getById(ID))
    }

    suspend fun update(data: CategoryModel) {
        dao.update(data)
    }

    @Transaction
    suspend fun insert(data: List<CategoryModel>) {
        dao.delete()
        dao.insert(data)
    }

    suspend fun insert(data: CategoryModel) {
        dao.insert(data)
    }

    fun getAllList(): Flow<List<CategoryModel>> {
        return dao.getAllList()
    }
}