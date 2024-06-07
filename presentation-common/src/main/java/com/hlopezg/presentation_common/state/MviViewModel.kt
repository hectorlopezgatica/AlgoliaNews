package com.hlopezg.presentation_common.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<T : Any, S : UiState<T>, A : UiAction, E : UiSingleEvent> :
    ViewModel() {
    private val _uiStateFlow: MutableStateFlow<S> by lazy {
        MutableStateFlow(initState())
    }

    val uiStateFlow: StateFlow<S> = _uiStateFlow
    private val actionFlow: MutableSharedFlow<A> = MutableSharedFlow()
    private val _singeEventFlow = Channel<E>()
    val singleEventFlow = _singeEventFlow.receiveAsFlow()

    init {
        viewModelScope.launch {
            actionFlow.collect {
                handleAction(it)
            }
        }
    }

    abstract fun initState(): S
    abstract fun handleAction(action: A)

    fun submitAction(action: A) {
        viewModelScope.launch {
            actionFlow.emit(action)
        }
    }

    fun submitState(state: S) {
        viewModelScope.launch {
            _uiStateFlow.value = state
        }
    }

    fun getState(): S {
        return _uiStateFlow.value
    }

    fun submitSingleEvent(event: E) {
        viewModelScope.launch {
            _singeEventFlow.send(event)
        }
    }
}