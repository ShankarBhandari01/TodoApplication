package com.shankar.todoapplication.base

sealed class UiState<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = -1
) {
    class Success<T>(data: T) : UiState<T>(data)
    class Loading<T>(data: T? = null) : UiState<T>(data)
    class Error<T>(message: String, code: Int? = -1, errorData: T? = null) : UiState<T>(errorData, message, code)

    class None<T> : UiState<T>()
}
