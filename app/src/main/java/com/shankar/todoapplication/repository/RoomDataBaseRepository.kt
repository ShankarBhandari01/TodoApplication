package com.shankar.todoapplication.repository

import com.shankar.todoapplication.database.AppDatabase
import com.shankar.todoapplication.database.IDao
import com.shankar.todoapplication.database.TaskDao
import com.shankar.todoapplication.model.CategoryModel
import kotlinx.coroutines.flow.Flow

class RoomDataBaseRepository(private val appDatabase: AppDatabase) : TaskDao {
    override suspend fun getById(ID: Int): Flow<CategoryModel> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(data: CategoryModel) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(data: List<CategoryModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun update(data: CategoryModel) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(data: CategoryModel) {
        TODO("Not yet implemented")
    }

}