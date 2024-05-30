package com.hlopezg.presentation_common.state

sealed class UiState<out T : Any> {
    data object Loading : UiState<Nothing>()
    data class Error(val errorMessage: String, val loadLocalData: Boolean = false) : UiState<Nothing>()
    data class Success<T : Any>(val data: T) : UiState<T>()
}
