package com.hlopezg.presentation_post.list

import androidx.lifecycle.viewModelScope
import com.hlopezg.domain.usecase.GetHitsUseCase
import com.hlopezg.presentation_common.state.MviViewModel
import com.hlopezg.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val hitsUseCase: GetHitsUseCase,
    private val postConverter: PostConverter,
) : MviViewModel<PostModel, UiState<PostModel>, HitListUiAction, HitListUiSingleEvent>() {
    override fun initState(): UiState<PostModel> = UiState.Loading

    override fun handleAction(action: HitListUiAction) {
        when (action) {
            HitListUiAction.Load -> {
                loadPost()
            }
            is HitListUiAction.SingleHitClick -> {


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