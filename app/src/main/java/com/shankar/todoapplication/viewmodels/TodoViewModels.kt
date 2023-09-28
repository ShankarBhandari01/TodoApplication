package com.shankar.todoapplication.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankar.todoapplication.base.UiState
import com.shankar.todoapplication.model.CategoryModel
import com.shankar.todoapplication.repository.RoomDataBaseRepository
import com.shankar.todoapplication.utils.doTryCatch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


import kotlinx.coroutines.launch


class TodoViewModels(private val roomDataBaseRepository: RoomDataBaseRepository) : ViewModel() {

    private var _getCategoryList = MutableStateFlow<UiState<List<CategoryModel>>>(UiState.None())

    val getCategoryList: StateFlow<UiState<List<CategoryModel>>> = _getCategoryList
    fun getCategory() {
        viewModelScope.launch {
            roomDataBaseRepository.getAllList()
                .catch { _getCategoryList.value = UiState.Error(it.message!!) }
                .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
                .collect {
                    _getCategoryList.value = UiState.Success(it)
                }
        }
    }


    fun insertCategory(categoryList: List<CategoryModel>) {
        viewModelScope.launch {
            roomDataBaseRepository.insert(categoryList)
        }
    }
}
