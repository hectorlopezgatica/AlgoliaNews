package com.hlopezg.presentation_post.list

import androidx.lifecycle.viewModelScope
import com.hlopezg.domain.entity.Result
import com.hlopezg.domain.usecase.GetHitsUseCase
import com.hlopezg.domain.usecase.GetLocalHitsUseCase
import com.hlopezg.presentation_common.state.MviViewModel
import com.hlopezg.presentation_common.state.UiState
import com.hlopezg.presentation_post.model.PostModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
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
    private val localHitsUseCase: GetLocalHitsUseCase,
    private val postConverter: PostConverter,
    private val localPostConverter: LocalPostConverter,
) : MviViewModel<PostModel, UiState<PostModel>, HitListUiAction, HitListUiSingleEvent>() {
    override fun initState(): UiState<PostModel> = UiState.Loading
    override fun handleAction(action: HitListUiAction) {
        when (action) {
            HitListUiAction.Load -> {
                loadPost()
            }

            HitListUiAction.Update -> {
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
                if(it is UiState.Error && it.loadLocalData){
                    loadLocalPost()
                }
                submitState(
                    it
                )
            }
        }
    }

    private suspend fun loadLocalPost(){
        localHitsUseCase.execute(GetLocalHitsUseCase.Request).map { result ->
            localPostConverter.convert(result)
        }.collect {
            submitState(
                it
            )
        }
    }
}