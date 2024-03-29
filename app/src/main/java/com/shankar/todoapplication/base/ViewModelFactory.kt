package com.shankar.todoapplication.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shankar.todoapplication.repository.RoomDataBaseRepository
import com.shankar.todoapplication.ui.login.LoginViewModel
import com.shankar.todoapplication.viewmodels.TodoViewModels

class ViewModelFactory(
    private val roomDataBaseRepository: RoomDataBaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModels::class.java)) {
            return TodoViewModels(roomDataBaseRepository) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return roomDataBaseRepository.let { LoginViewModel(it) as T }

        }
        throw IllegalArgumentException("Unknown class name")
    }

}