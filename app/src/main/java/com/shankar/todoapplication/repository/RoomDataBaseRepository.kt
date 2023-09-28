package com.shankar.todoapplication.repository

import androidx.room.Transaction
import com.shankar.todoapplication.database.AppDatabase
import com.shankar.todoapplication.database.BaseDao
import com.shankar.todoapplication.database.DatabaseManager
import com.shankar.todoapplication.model.CategoryModel
import kotlinx.coroutines.flow.Flow

class RoomDataBaseRepository<T>(private val dao: BaseDao<T>) : BaseDao<T> {

    override fun getById(ID: Int): Flow<T> {
        return dao.getById(ID)
    }

    override suspend fun delete() {
        dao.delete()
    }

    override suspend fun update(data: T) {
        dao.update(data)
    }

    @Transaction
    override suspend fun insert(data: List<T>) {
        delete()
        dao.insert(data)
    }

    override suspend fun insert(data: T) {
        dao.insert(data)
    }

    override fun getAllList(): Flow<List<T>> {
        return dao.getAllList()
    }
}