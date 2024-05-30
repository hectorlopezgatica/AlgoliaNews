package com.hlopezg.presentation_post.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hlopezg.domain.entity.Result
import com.hlopezg.domain.usecase.GetHitsUseCase
import com.hlopezg.presentation_common.state.MviViewModel
import com.hlopezg.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PostState(
    val postModel: PostModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class PostViewModel @Inject constructor(
    private val hitsUseCase: GetHitsUseCase,
    private val postConverter: PostConverter,
) : MviViewModel<PostModel, UiState<PostModel>, HitListUiAction, HitListUiSingleEvent>() {
    /*private val _uiState = MutableStateFlow(PostState())
    val uiState: StateFlow<PostState> = _uiState.asStateFlow()*/
    override fun initState(): UiState<PostModel> = UiState.Loading
    override fun handleAction(action: HitListUiAction) {
        when (action) {
            HitListUiAction.Load -> {
                loadPost()
            }

            is HitListUiAction.SingleHitClick -> {
                submitSingleEvent(
                    HitListUiSingleEvent.OpenHitDetailScreen(action.hit)
                )
            }
        }
    }

    private fun loadPost() {
        viewModelScope.launch {
            hitsUseCase.execute(GetHitsUseCase.Request).map { result ->
                postConverter.convert(result)
            }.collect {
                submitState(
                    it
                )
            }
        }
    }
}