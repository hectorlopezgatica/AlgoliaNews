package com.hlopezg.presentation_common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hlopezg.presentation_common.state.UiState

@Composable
fun <T : Any> CommonScreen(state: UiState<T>, onSuccess: @Composable (T) -> Unit) {
    when (state) {
        UiState.Loading -> {
            LoadingComponentList()
        }

        is UiState.Error -> {
            ErrorComponent(state.errorMessage)
        }

        is UiState.Success -> {
            onSuccess(state.data)
        }
    }
}

@Composable
fun ErrorComponent(errorMessage: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Snackbar {
            Text(text = errorMessage)
        }
    }
}